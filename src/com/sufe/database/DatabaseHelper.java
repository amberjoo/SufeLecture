package com.sufe.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "lecture.db";
	private static final int DATABASE_VERSION = 1;//
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE IF NOT EXISTS School("
			    +"SCno text PRIMARY KEY,"
				+"SCname text "
				+ "); ");
		db.execSQL("CREATE TABLE IF NOT EXISTS Major("
			    +"Mno text PRIMARY KEY,"
				+"Mname text, "
			    +"SCno text"
				+ "); ");
		db.execSQL("CREATE TABLE IF NOT EXISTS Student("
			    +"Sno text PRIMARY KEY,"
				+"Sname text,"
				+"Password text,"
			    +"Tel text,"
			    +"Email text,"
			    +"Mno text,"
			    +"SCno text"
				+ "); ");
		db.execSQL("CREATE TABLE IF NOT EXISTS PlaceHolder("
			    +"Pno integer PRIMARY KEY  AUTOINCREMENT,"
				+"HolderStatus integer, "
			    +"Lno text,"
			    +"Sno text,"
			    +"Ano text"
				+ "); ");
		db.execSQL("CREATE TABLE IF NOT EXISTS Alert("
			    +"Ano text PRIMARY KEY,"
			    +"Status integer,"
			    +"Time timestamp,"
			    +"Note text"
				+ "); ");
		db.execSQL("CREATE TABLE IF NOT EXISTS Participate("
			    +"Pno integer PRIMARY KEY  AUTOINCREMENT,"
				+"Pstatus integer, "
			    +"Lno text,"
			    +"Sno text,"
			    +"Ano text"
				+ "); ");
		db.execSQL("CREATE TABLE IF NOT EXISTS Comment("
			    +"Cno text PRIMARY KEY,"
				+"Time timestamp DEFAULT CURRENT_TIMESTAMP, "
			    +"Content text,"
			    +"Lno text,"
			    +"Sno text"
				+ "); ");
		db.execSQL("CREATE TABLE IF NOT EXISTS Lecture("
			    +"Lno text PRIMARY KEY,"
				+"Lname text, "
			    +"Time timestamp,"
			    +"Keyword text,"
			    +"Picture text,"
			    +"Place text,"
			    +"Lector text,"
			    +"Organizer text,"
			    +"Type text,"
			    +"SeatNumber integer,"
			    +"PUno text"
				+ "); ");
		db.execSQL("CREATE TABLE IF NOT EXISTS Publisher("
			    +"PUno text PRIMARY KEY,"
				+"PUname text, "
			    +"Manager text,"
			    +"PUPassword text,"
			    +"Tel text,"
			    +"Email text"
				+ "); ");
		
	}

	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}
}
