package com.sufe.lecture.biz.model;

import android.text.TextUtils;

public class MineForumBO {
	public int imageId;
	public String breifTitle;
	public String time;
	public String Lno;
	public String isTip;//��ӦAlert����Status�ֶΣ���˼�ǣ��Ƿ���Ҫ����
	public String isGiveup;//��ӦPlaceHolder��HolderStatus,����Participate����Pstatus,��˼�ǣ��Ƿ�����μ�
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
