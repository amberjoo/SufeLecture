package com.sufe.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

public class DatabaseUtil {
	private DatabaseHelper mDatabaseHelper;
	public DatabaseUtil(Context mContext){
		mDatabaseHelper = new DatabaseHelper(mContext);
	}
	//update school set sname=1? where sno = 2? 
	//ContentValues values =  new ContentValues();
	//values.put("sname",yanghaibo);
	//update("school",values,"sno=?",new String[]{1234});
	//
	public int update(String table,ContentValues values,String whereClause,String []whereArgs){
		if(TextUtils.isEmpty(table)){
			throw new IllegalArgumentException("Update table name is null...");
		}
		int i = -1;
		try{
			SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
			i = db.update(table, values, whereClause, whereArgs);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return i;
	}
	public void insert(String table,ContentValues values){
		if (TextUtils.isEmpty(table)) {
			throw new IllegalArgumentException("insert table name is null...");
		}
		SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
		db.insert(table, null, values);
	}
	public void delete(String table,String whereClause,String []whereArgs){
		if (TextUtils.isEmpty(table)) {
			throw new IllegalArgumentException("delte table name is null!");
		}
		SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
		db.delete(table, whereClause, whereArgs);
	}
	public void exeSQL(String sql){
		if(TextUtils.isEmpty(sql)){
			throw new IllegalArgumentException("Args sql is empty!");
		}
		SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
		db.execSQL(sql);
	}
	public void exeSQL(String sql,Object []args){
		if(TextUtils.isEmpty(sql)){
			throw new IllegalArgumentException("Args sql is empty!");
		}
		SQLiteDatabase db = mDatabaseHelper.getWritableDatabase();
		db.execSQL(sql,args);
	}
	public Cursor queryForCursor(String sql, String []args){
		if(TextUtils.isEmpty(sql)){
			throw new IllegalArgumentException("Args sql is empty!");
		}
		SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
		Cursor c = db.rawQuery(sql, args);
		return c;
	}
	public Cursor queryForCursor(String sql){
		if(TextUtils.isEmpty(sql)){
			throw new IllegalArgumentException("Args sql is empty!");
		}
		SQLiteDatabase db = mDatabaseHelper.getReadableDatabase();
		Cursor c = db.rawQuery(sql,null);
		return c;
	}
	
}
