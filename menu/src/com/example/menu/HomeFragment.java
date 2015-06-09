package com.example.menu;

import java.io.File;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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
	
	private static final String TEMP_PHOTO_FILE = "temp.jpg";       // �ӽ� ��������
    private static final int REQ_CODE_PICK_IMAGE = 0;
	
 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        
        representPic = (ImageView) v.findViewById(R.id.representPic);
        chattingBut = (Button) v.findViewById(R.id.chattingBut);
        xxx = (TextView) v.findViewById(R.id.DdayText);
        
        representPic.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				/* call gallery app	 */
				Intent intent = new Intent(
                Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				File tempFile = new File(Environment.getExternalStorageDirectory() + "/temp.jpg");
			    Uri tempUri = Uri.fromFile(tempFile);
                intent.setType("image/*");              // ��� �̹���
                intent.putExtra("crop", "true");        // Crop��� Ȱ��ȭ
                intent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);     // �ӽ����� ��
                intent.putExtra("outputFormat",         // ���˹��
                        Bitmap.CompressFormat.JPEG.toString());
 
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

	 /** �ٽ� ��Ƽ��Ƽ�� �����Ͽ����� �̹����� ���� */
	 public void onActivityResult(int requestCode, int resultCode, Intent imageData) {
	     super.onActivityResult(requestCode, resultCode, imageData);
	
	     switch (requestCode) {
	     case REQ_CODE_PICK_IMAGE:
	         if (resultCode == Activity.RESULT_OK) {
	             if (imageData != null) {
	                 String filePath = Environment.getExternalStorageDirectory()
	                         + "/temp.jpg";
	
	                 Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
	                 // temp.jpg������ Bitmap���� ���ڵ��Ѵ�.
	
	                 representPic.setImageBitmap(selectedImage); 
	                 // temp.jpg������ �̹����信 �����.
	             }
	         }
	         break;
	     }
	 } 
}