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

import com.example.calendar.Schedule;
import com.example.calendar.ScheduleAddResult;
import com.example.calendar.ScheduleListResult;
import com.example.calendar.ScheduleRemoveResult;
import com.example.home.BubbleListResult;
import com.example.letter.ResultDelLetter;
import com.example.letter.ResultLetter;
import com.example.letter.ResultLetterList;
import com.example.letter.ResultWriteLetter;
import com.example.menu.notice.DataNotice;
import com.example.menu.notice.ResultNotice;
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
	//public static final String SERVER = "http://192.168.219.90:3000";
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
	//2. find partner
	public static final String FIND_PARTNER = SERVER + "/link/findpartner";
	public void findPartner(Context context, String partner_mail, int user_id, final OnResultListener<FindPartnerResult> listener){
		RequestParams params = new RequestParams();
		params.put("partner_email", partner_mail);
		params.put("user_id", ""+user_id);
		
		client.post(context, FIND_PARTNER, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, org.apache.http.Header[] headers,
					String responseString) {
				FindPartnerResult result = gson.fromJson(responseString, FindPartnerResult.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, org.apache.http.Header[] headers,
					String responseString, Throwable throwable) {
				// TODO Auto-generated method stub
				
			}
		}); 
	}
	
	//3. check link request
	public static final String CHECK_LINK_REQUEST = SERVER + "/link/checkreq";
	public void checkReq(Context context, int user_id, final OnResultListener<CheckReqResult> listener){
		RequestParams params = new RequestParams();
		params.put("user_id", ""+user_id);
		
		client.post(context, CHECK_LINK_REQUEST, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, org.apache.http.Header[] headers,
					String responseString) {
				CheckReqResult result = gson.fromJson(responseString, CheckReqResult.class);
				listener.onSuccess(result);
				
			}
			
			@Override
			public void onFailure(int statusCode, org.apache.http.Header[] headers,
					String responseString, Throwable throwable) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	//5.letter list
	public static final String LETTER_LIST = SERVER + "/letter/letterlist";
	public void getLetterList(Context context, int link_id, final OnResultListener<ResultLetterList> listener){
		RequestParams params = new RequestParams();
		params.put("link_id", ""+link_id);
		client.post(context,  LETTER_LIST, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				ResultLetterList result = gson.fromJson(responseString, ResultLetterList.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	//6. read letter
	public static final String READ_LETTER = SERVER + "/letter/readletter";
	public void getLetter(Context context, int letter_id, final OnResultListener<ResultLetter> listener)
	{
		RequestParams params = new RequestParams();
		params.put("letter_id", ""+letter_id);
		
		client.post(context, READ_LETTER, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				ResultLetter result = gson.fromJson(responseString, ResultLetter.class);
				listener.onSuccess(result);
				
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	//7. write letter
	public static final String WRITE_LETTER = SERVER + "/letter/writeletter";
	public void sendLetter(Context context, int link_id, int user_id, String letter_content, String date, final OnResultListener<ResultWriteLetter> listener)
	{
		RequestParams params = new RequestParams();
		params.put("link_id", ""+link_id);
		params.put("user_id", ""+user_id);
		params.put("letter_content", ""+letter_content);
		params.put("date", ""+date);
		
		client.post(context, WRITE_LETTER, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				ResultWriteLetter result = gson.fromJson(responseString, ResultWriteLetter.class);
				listener.onSuccess(result);
				
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	//8. delete letter
	public static final String DELETE_LETTER = SERVER + "letter/deleteletter";
	public void deleteLetter(Context context, int letter_id, final OnResultListener<ResultDelLetter> listener)
	{
		RequestParams params = new RequestParams();
		params.put("letter_id", ""+letter_id);
		
		client.post(context, DELETE_LETTER, params, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					String responseString) {
				ResultDelLetter result = gson.fromJson(responseString, ResultDelLetter.class);
				listener.onSuccess(result);
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					String responseString, Throwable throwable) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	//9. calendar schedule add
		public static final String CALENDAR_ADD = SERVER + "/calendar/add";
		public void getCalendarAdd(Context context, int modi, Schedule s, final OnResultListener<ScheduleAddResult> listener)
		{
			RequestParams params = new RequestParams();
			params.put("Modi", ""+modi);
			params.put("LinkId", "1");
			params.put("Id", ""+s.calendar_id);
			params.put("date", s.date);
			params.put("Name", s.calendar_name);
			params.put("Place", s.place);
			params.put("Hour", ""+s.hour);
			params.put("Min", ""+s.min);
			params.put("Reply", ""+s.reply);
			params.put("Prealarm", ""+s.prealarm);
			params.put("Sound", ""+s.sound);
			client.post(context, CALENDAR_ADD, params, new TextHttpResponseHandler() {
				
				@Override
				public void onFailure(int statusCode,
						org.apache.http.Header[] headers,
						String responseString, Throwable throwable) {
					listener.onFail(statusCode);
					
				}

				@Override
				public void onSuccess(int statusCode,
						org.apache.http.Header[] headers, String responseString) {
					ScheduleAddResult result = gson.fromJson(responseString, ScheduleAddResult.class);
					listener.onSuccess(result);
				}
			});
		}
		
		//10. calendar schedule remove
		public static final String CALENDAR_REMOVE = SERVER + "/calendar/remove";
		public void getCalendarRemove(Context context, int id, final OnResultListener<ScheduleRemoveResult> listener)
		{
			RequestParams params = new RequestParams();
			params.put("Id", ""+id);
			client.post(context, CALENDAR_REMOVE, params, new TextHttpResponseHandler() {
				

				@Override
				public void onFailure(int statusCode,
						org.apache.http.Header[] headers,
						String responseString, Throwable throwable) {
					listener.onFail(statusCode);
				}

				@Override
				public void onSuccess(int statusCode,
						org.apache.http.Header[] headers, String responseString) {
					ScheduleRemoveResult result = gson.fromJson(responseString, ScheduleRemoveResult.class);
					listener.onSuccess(result);
				}
			});
		}
		
		//11. calendar schedule list
		public static final String CALENDAR_LIST = SERVER + "/calendar/list";
		public void getCalendarList(Context context, String date, final OnResultListener<ScheduleListResult> listener)
		{
			RequestParams params = new RequestParams();
			params.put("Date", date);
			params.put("LinkId", "1");
			client.post(context, CALENDAR_LIST, params, new TextHttpResponseHandler() {
				

				@Override
				public void onFailure(int statusCode,
						org.apache.http.Header[] headers,
						String responseString, Throwable throwable) {
					listener.onFail(statusCode);
					
				}

				@Override
				public void onSuccess(int statusCode,
						org.apache.http.Header[] headers, String responseString) {
					ScheduleListResult result = gson.fromJson(responseString, ScheduleListResult.class);
					listener.onSuccess(result);
					
				}
			});
		}
		
		//14. chatting messages load
		public static final String CHATTING = SERVER + "/chatting";
		public void getChatting(Context context, final OnResultListener<BubbleListResult> listener)
		{
			RequestParams params = new RequestParams();
			params.put("LinkId", "1");
			client.get(context, CHATTING, params, new TextHttpResponseHandler() {
				

				@Override
				public void onFailure(int statusCode,
						org.apache.http.Header[] headers,
						String responseString, Throwable throwable) {
					listener.onFail(statusCode);
					
				}

				@Override
				public void onSuccess(int statusCode,
						org.apache.http.Header[] headers, String responseString) {
					BubbleListResult result = gson.fromJson(responseString, BubbleListResult.class);
					listener.onSuccess(result);
					
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
	
	//17. notice and terms
	public static final String NOTICE = SERVER + "/notice";
	public void getNotice(Context context, final OnResultListener<ResultNotice> listener)
	{
		client.get(context, NOTICE, new TextHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, org.apache.http.Header[] headers,
					String responseString) {
				ResultNotice result = gson.fromJson(responseString, ResultNotice.class);
				listener.onSuccess(result);				
				
			}
			
			@Override
			public void onFailure(int statusCode, org.apache.http.Header[] headers,
					String responseString, Throwable throwable) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
}
