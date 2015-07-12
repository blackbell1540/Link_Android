package com.example.home;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.example.menu.R;
import com.example.menu.R.id;
import com.example.menu.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeFragment extends Fragment  {
	ImageView representPic;
	Button chattingBut;
	TextView xxx;
	static Calendar mCal = new GregorianCalendar(2014, 4, 20);
	
    private static final int REQ_CODE_PICK_IMAGE = 0;
    String filePath = Environment.getExternalStorageDirectory() + "/repPic.jpg";
    Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
	
 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        
        representPic = (ImageView) v.findViewById(R.id.representPic);
        chattingBut = (Button) v.findViewById(R.id.chattingBut);
        xxx = (TextView) v.findViewById(R.id.DdayText);
        
        representPic.setImageBitmap(selectedImage); 
        representPic.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/* call gallery app	 */
				Intent intent = new Intent(
						Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				File tempFile = new File(Environment.getExternalStorageDirectory() + "/repPic.jpg");
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
        
        chattingBut.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getActivity(), ChattingActivity.class);
				startActivity(i);
			}
		});
        
        /* calculate dday
         */
        Calendar today = new GregorianCalendar();
        today.set(today.get(Calendar.YEAR), today.get(Calendar.MONTH)+1, today.get(Calendar.DAY_OF_MONTH));
        xxx.setText( ""+(int) ((today.getTimeInMillis()-mCal.getTimeInMillis())
        		/(24*60*60*1000)) );

        return v;
    }

	 /** 다시 액티비티로 복귀하였을때 이미지를 셋팅 */
	 public void onActivityResult(int requestCode, int resultCode, Intent imageData) {
	     super.onActivityResult(requestCode, resultCode, imageData);
	
	     switch (requestCode) {
	     case REQ_CODE_PICK_IMAGE:
	         if (resultCode == Activity.RESULT_OK) {
	             if (imageData != null) {
	                 filePath = Environment.getExternalStorageDirectory()
	                         + "/repPic.jpg";
	
	                 selectedImage = BitmapFactory.decodeFile(filePath);
	                 // temp.jpg파일을 Bitmap으로 디코딩한다.
	
	                 representPic.setImageBitmap(selectedImage); 
	                 // temp.jpg파일을 이미지뷰에 씌운다.
	             }
	         }
	         break;
	     }
	 } 
}