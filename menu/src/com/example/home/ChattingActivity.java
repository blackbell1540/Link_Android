package com.example.home;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.menu.NetworkManager;
import com.example.menu.NetworkManager.OnResultListener;
import com.example.menu.R;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.emitter.Emitter.Listener;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;


public class ChattingActivity extends FragmentActivity {
	ListView list;
	Button menu, imoticon, ok;
	EditText text;
	BubbleAdapter adapter;
	Socket socket;
	FragmentManager fm = getSupportFragmentManager();
	
	private static final int REQ_CODE_PICK_IMAGE = 0;
    String filePath = Environment.getExternalStorageDirectory() + "/tempPic.jpg";
    Bitmap selectedImage = BitmapFactory.decodeFile(filePath);

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chatting);
		
        list = (ListView) findViewById(R.id.chattinglist);
        adapter = new BubbleAdapter(this);
        list.setAdapter(adapter);
        
        menu = (Button) findViewById(R.id.chat_menu);
        imoticon = (Button) findViewById(R.id.imoticon);
        ok = (Button) findViewById(R.id.chat_ok);
        text = (EditText) findViewById(R.id.chat_text);
        
        try {
        	//socket = IO.socket("http://192.168.219.90:3000");
			socket = IO.socket("http://125.180.57.26:12345");
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        socket.on("message", onMessage);
        socket.connect();
        socket.emit("join", "1");
        
        ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//send LINK_ID, SENDER_ID, MESSAGE, PICTURE
				JSONObject obj = new JSONObject();
				try {
					obj.put("LINK_ID", "1");
					obj.put("SENDER_ID", "2");
					obj.put("MESSAGE", text.getText().toString());
					obj.put("PICTURE", "-1");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				text.setText("");
				socket.emit("message", obj);
			}
		});
        
        menu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//take picture
				Intent intent = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				File tempFile = new File(Environment.getExternalStorageDirectory() + "/tempPic.jpg");
			    Uri tempUri = Uri.fromFile(tempFile);
                intent.setType("image/*");              // 모든 이미지
                intent.putExtra("crop", "true");        // Crop기능 활성화
                intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);     // 임시파일 생성
                intent.putExtra("outputFormat",         // 포맷방식
                        Bitmap.CompressFormat.JPEG.toString());
                intent.putExtra("outputX", 500);
                intent.putExtra("outputY", 500);
 
                startActivityForResult(intent, REQ_CODE_PICK_IMAGE);
			}
		});
        
        initChatting();
        
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Bubble b = (Bubble) list.getItemAtPosition(position);
				if (b.picture != null) {
					PicAddFragment df = new PicAddFragment();
					Bundle args = new Bundle();
					args.putString("date", b.date);
					args.putString("name", "/testpic" + b.sender_id + b.date);
					args.putString("picture", b.picture);
					df.setArguments(args);
					df.show(fm, "Dialog Fragment");
				}
			}
        	
        });
        
    }
	
	private Listener onMessage = new Emitter.Listener() {
		@Override
		public void call(final Object... args) {
			ChattingActivity.this.runOnUiThread(new Runnable() {
	            @Override
	            public void run() {
	            	JSONObject obj = (JSONObject)args[0];
	    			Bubble b = new Bubble();
	    			try {
	    				b.sender_id = obj.getInt("SENDER_ID");
	    				b.date = obj.getString("DATE");
	    				b.message = obj.getString("MESSAGE");
	    				b.picture = obj.getString("PICTURE");
	    			} catch (JSONException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    			if (b.sender_id == 2) 
	    				b.type = b.SEND_BUBBLE;
	    			else
	    				b.type = b.RECEIVE_BUBBLE;
	    			adapter.add(b);
	    			adapter.clear();
					initChatting();
	            }
	        });
		}
	};
	
	public void onActivityResult(int requestCode, int resultCode, Intent imageData) {
	     super.onActivityResult(requestCode, resultCode, imageData);
	
	     switch (requestCode) {
	     case REQ_CODE_PICK_IMAGE:
	         if (resultCode == Activity.RESULT_OK) {
	             if (imageData != null) {
	            	//make obj
	                 JSONObject obj = new JSONObject();
	 				try {
	 					
	 					File tempFile = new File(Environment.getExternalStorageDirectory() + "/tempPic.jpg");
	 					FileInputStream imageInFile = new FileInputStream(tempFile);
	            	    byte imageArray[] = new byte[(int) tempFile.length()];
	            	    System.out.println("length : " + tempFile.length());
	            	    imageInFile.read(imageArray);
	            	    
	            	    // Converting Image byte array into Base64 String
	            	    String imageDataString = Base64.encodeToString(imageArray, Base64.DEFAULT);
	            	    
	 					obj.put("LINK_ID", "1");
	 					obj.put("SENDER_ID", "2");
	 					obj.put("MESSAGE", "-1");
	 					obj.put("PICTURE", imageDataString);
	 					
	 					//emit message
		 				socket.emit("message", obj);
		 				
		 				
	 				} catch (JSONException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

	 				
	             }
	         }
	         break;
	     }
	 } 
	
	private void initChatting() {
		NetworkManager.getInstnace().getChatting(ChattingActivity.this, new OnResultListener<BubbleListResult>() {

			@Override
			public void onSuccess(BubbleListResult r) {
				// TODO Auto-generated method stub
				if(r.success == 1) {
					for(Bubble i : r.result) {
						if (i.sender_id == 2) 
							i.type = i.SEND_BUBBLE;
						else
							i.type = i.RECEIVE_BUBBLE;
						adapter.add(i);
					}
				}
			}

			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				Log.d("net", ""+code);
			}
			
		});
	}

}
