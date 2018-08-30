package com.sufe.lecture.biz.model;

import android.text.TextUtils;
import android.util.Log;

public class CommentBO {
	public String Content;
	public String time;
	public String Sno;

	public String time(){
		if(time.length() > 19){
			return time.substring(0, time.length()-4);  
		}
		else{
			return time;
		}
	}
}
