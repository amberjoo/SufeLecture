package com.sufe.lecture.biz.model;

import android.text.TextUtils;
import android.util.Log;

public class AllForumBO {
	public int imageId;
	public String breifTitle;
	public String time;
	public String Lno;

	public String time(){
		if(time.length() > 19){
			return time.substring(0, time.length()-4);  
		}
		else{
			return time;
		}
	}
//	主题只保留到10个字
	public String title(){
		if(!TextUtils.isEmpty(breifTitle) && breifTitle.length() > 10){
			return breifTitle.substring(0, 10)+"...";
		}
		return breifTitle;
	}
}
