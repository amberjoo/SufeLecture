package com.sufe.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String getCurrentDate(){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(now); 
	}
}
