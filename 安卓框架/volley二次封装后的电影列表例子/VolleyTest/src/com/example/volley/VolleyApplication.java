package com.example.volley;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Application;

public class VolleyApplication extends Application {

	/**
	 * 01. ����  �������
	 * 02. �� ������� ���뵽 AndroidMain.xml��
	 * 03. 
	 */
	
	private static RequestQueue queue;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		queue=Volley.newRequestQueue(getApplicationContext());
	}
	
	//���
	public static RequestQueue getQueue(){
		return queue;
	}
	
	
}
