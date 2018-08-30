package com.sufe.lecture.biz.model;

import android.text.TextUtils;

public class ToadyForumBO {
	public int imageId;
	public String breifTitle;
	public String Lno;
	public String title(){
		if(!TextUtils.isEmpty(breifTitle) && breifTitle.length() > 10){
			return breifTitle.substring(0, 10)+"...";
		}
		return breifTitle;
	}
}
