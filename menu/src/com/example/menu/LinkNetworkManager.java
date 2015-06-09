package com.example.menu;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;

import android.content.Context;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

public class LinkNetworkManager {
	private static LinkNetworkManager Netinstance;
	public static LinkNetworkManager getInstnace() {
		if (Netinstance == null) {
			Netinstance = new com.example.menu.LinkNetworkManager();
		}
		return Netinstance;
	}
	
	AsyncHttpClient client;
	private LinkNetworkManager() {
		
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
		public void onSuccess(T movie);
		public void onFail(int code);
	}
	
	
	
	Gson gson = new Gson();
	public static final String SERVER = "http://192.168.123.115:3000";
	
	//15. user info
	public static final String USER_INFO = SERVER + "/user/profile";
	public void getUserInfo(Context context, int userId, final OnResultListener<UserInfo> listener)
	{
		RequestParams params = new RequestParams();
		params.put("user_id", ""+userId);
		client.post(context, USER_INFO, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				UserInfo result = gson.fromJson(responseString, UserInfo.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				listener.onFail(statusCode);
				
			}
		});
	}
}
