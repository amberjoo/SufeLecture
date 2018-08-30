package com.sufe.lecture.biz.model;

import android.text.TextUtils;

public class MineForumBO {
	public int imageId;
	public String breifTitle;
	public String time;
	public String Lno;
	public String isTip;//对应Alert表中Status字段，意思是：是否需要提醒
	public String isGiveup;//对应PlaceHolder中HolderStatus,或者Participate表中Pstatus,意思是：是否放弃参加
	public String time(){
		if(time.length() > 19){
			return time.substring(0, time.length()-4);
		}
		else{
			return time;
		}
	}
	public String title(){
		if(!TextUtils.isEmpty(breifTitle) && breifTitle.length() > 10){
			return breifTitle.substring(0, 10)+"...";
		}
		return breifTitle;
	}
}
