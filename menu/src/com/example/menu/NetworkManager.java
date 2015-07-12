package com.example.menu;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.http.client.HttpClient;

import android.content.Context;
import android.preference.PreferenceActivity.Header;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;


public class NetworkManager {
	private static NetworkManager instance;
	public static NetworkManager getInstnace() {
		if (instance == null) {
			instance = new NetworkManager();
		}
		return instance;
	}
	
	AsyncHttpClient client;
	private NetworkManager() {
		
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			trustStore.load(null, null);
			MySSLSocketFactory socketFactory = new MySSLSocketFactory(trustStore);
			socketFactory.setHostnameVerifier(MySSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
			client = new AsyncHttpClient();
			client.setSSLSocketFactory(socketFactory);			
			client.setCookieStore(new PersistentCookieStore(LinkApplication.getContext()));
			client.setTimeout(30000);
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		}
	}
	
	public HttpClient getHttpClient() {
		return client.getHttpClient();
	}
	
	public interface OnResultListener<T> {
		public void onSuccess(T result);
		public void onFail(int code);
	}
	
	
	Gson gson = new Gson();
	public static final String SERVER = "http://125.180.57.26:12345";

	//1. user login & join
	public static final String USER_LOGIN_JOIN = SERVER + "/user/login";
	public void getUserLoginJoin(Context context, String e_mail, String phone_number, final OnResultListener<LoginResult> listener)
	{
		RequestParams params = new RequestParams();
		params.put("e_mail", ""+e_mail);
		params.put("phone_number", ""+phone_number);
		client.post(context, USER_LOGIN_JOIN, params, new TextHttpResponseHandler() {

			@Override
			public void onFailure(int statusCode,
					org.apache.http.Header[] headers, String responseString,
					Throwable throwable) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(int statusCode,
					org.apache.http.Header[] headers, String responseString) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	//15. user info
	public static final String USER_INFO = SERVER + "/user/profile";
	public void getUserInfo(Context context, int userId, final OnResultListener<UserInfo> listener)
	{
		RequestParams params = new RequestParams();
		params.put("user_id", ""+userId);
		client.post(context, USER_INFO, params, new TextHttpResponseHandler() {

			@Override
			public void onFailure(int statusCode,
					org.apache.http.Header[] headers, String responseString,
					Throwable throwable) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onSuccess(int statusCode,
					org.apache.http.Header[] headers, String responseString) {
				// TODO Auto-generated method stub
				
			}
		});
	}

}
