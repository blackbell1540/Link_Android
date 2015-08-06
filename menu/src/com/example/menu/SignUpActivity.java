package com.example.menu;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.menu.NetworkManager.OnResultListener;

public class SignUpActivity extends Activity{

	//views
	EditText editFindPartner, editInvitePartner;
	Button buttonFindPartner, buttonInvitePartner;
	String partenrMail, partnerPhone;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_sign_up);
	    
	    //find views
	    editFindPartner = (EditText)findViewById(R.id.editFindPartner);
	    editInvitePartner = (EditText)findViewById(R.id.editInvitePartner);
	    

	    //findPartner button click
	    //button find
	    buttonFindPartner = (Button)findViewById(R.id.buttonFindPartner);
	    buttonFindPartner.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				partenrMail = editFindPartner.getText().toString();
				
				if(partenrMail.length() != 0)
				{ findPartner(); }
				else
				{ Toast.makeText(SignUpActivity.this, "������ email�� �Է����ּ���", Toast.LENGTH_SHORT).show(); }
			}
		});
	    
	    //invite partner button click
	    buttonInvitePartner = (Button)findViewById(R.id.buttonInvitePartner);
	    buttonInvitePartner.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				partnerPhone = editInvitePartner.getText().toString();
				if(partnerPhone.length() > 0)
				{ sendSMS(partnerPhone); }
				else
				{ Toast.makeText(SignUpActivity.this, "�ڵ��� ��ȣ�� �Է����ּ���",	Toast.LENGTH_SHORT).show(); }
			}
		});
	    
	}
	
	//find partner
	public void findPartner()
	{
		//int user_id = SharedPreferenceManager.getInstance().getUserId();
		int user_id = 17;
		NetworkManager.getInstnace().findPartner(SignUpActivity.this, partenrMail, user_id, new OnResultListener<FindPartnerResult>() {
			
			@Override
			public void onSuccess(FindPartnerResult result) {
				if(result.message.equals("OK"))
				{
					//move waiting Activity
					SharedPreferenceManager.getInstance().setUserId(17);
					Intent intent = new Intent(SignUpActivity.this, WaitingActivity.class);
					startActivity(intent);
					finish();
				}else
				{	//toast : there is no partner
					Toast.makeText(SignUpActivity.this, result.message, Toast.LENGTH_SHORT).show();
				}
			}
			
			@Override
			public void onFail(int code) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	//send SMS message - invite
	public void sendSMS(String phone)
	{
		Log.i("send text", phone);
		PendingIntent sentIntent = PendingIntent.getBroadcast(SignUpActivity.this, 0, new Intent("SMS_SENT_ACTION"), 0);
		PendingIntent deliveredIntent = PendingIntent.getBroadcast(SignUpActivity.this, 0, new Intent("SMS_DELIVERED_ACTION"), 0);
    
		//register receiver - send message result
		registerReceiver(new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					//���� ����
					Toast.makeText(SignUpActivity.this, "�޽��� ���� ����", Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					//���� ����
					Toast.makeText(SignUpActivity.this, "�޽��� ���� ����", Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					//���� ���� �ƴ�
					Toast.makeText(SignUpActivity.this, "���� ������ �ƴմϴ�", Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					//���� ���� ����
					Toast.makeText(SignUpActivity.this, "���� ������ �����ֽ��ϴ�", Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					//PDU ����
					Toast.makeText(SignUpActivity.this, "PDU NULL", Toast.LENGTH_SHORT).show();
					break;
				}
				
			}
		}, new IntentFilter("SMS_SENT_ACTION"));
		
		//send message status
		registerReceiver(new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(SignUpActivity.this, "���� �Ϸ�", Toast.LENGTH_SHORT).show();
					break;
				case Activity.RESULT_CANCELED:
					Toast.makeText(SignUpActivity.this, "���� ����", Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
				}
				
			}
		}, new IntentFilter("SMS_DELIVERED_ACTION"));
		
		//sms manager
	    SmsManager mSmsManager = SmsManager.getDefault();
	    mSmsManager.sendTextMessage(phone, null, "���� ��ġ�� �ּ���", sentIntent, deliveredIntent);
	}
	

	
	
}
