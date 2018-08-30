package com.sufe.application;

import android.app.Application;

import com.sufe.lecture.biz.model.UserBO;

public class SufeApplication extends Application {
	private static UserBO USER;
	@Override
	public void onCreate() {
		super.onCreate();
		USER = new UserBO();
	}
	public static void setSno(String sno){
		USER.Sno = sno;
	}
	public static String getSno(){
		return USER.Sno;
	}
}
