package com.sufe.lecture.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.sufe.database.DatabaseUtil;
import com.sufe.lecture.R;
import com.sufe.lecture.adapter.AllForumAdapter;
import com.sufe.lecture.biz.model.AllForumBO;

public class SearchActivity extends Activity implements View.OnClickListener {
	private Button btn_search_back;
	private Button btn_search_set;
	private EditText searchText;
	private DatabaseUtil dbUtil;
	private ArrayList<AllForumBO> mData = new ArrayList<AllForumBO>();
	private AllForumAdapter adapter;
	private ListView lv_search_show;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		
		btn_search_back = (Button) findViewById(R.id.btn_search_back);
		btn_search_set = (Button) findViewById(R.id.btn_search_set);
		searchText = (EditText) findViewById(R.id.searchText);
		lv_search_show = (ListView) findViewById(R.id.lv_search_show);
		btn_search_back.setOnClickListener(this);
		btn_search_set.setOnClickListener(this);
		
		if (dbUtil == null) {
			dbUtil = new DatabaseUtil(this);
		}

}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.btn_search_back:
//			Intent intent = new Intent(SearchActivity.this,MainActivity.class);
//			startActivity(intent);
			finish();
			break;
		case R.id.btn_search_set:
			search();
			break;
		}
	}

	private void search() {
		// TODO Auto-generated method stub
		String text = searchText.getText().toString().trim();
		if(!text.equals("")){
			Cursor c = queryLectureCursor(text);
			initData(c);
			adapter = new AllForumAdapter(mData, this);
			lv_search_show.setAdapter(adapter);
			adapter.notifyDataSetChanged();
		}
		else {
			Toast.makeText(this, "ÇëÊäÈëËÑË÷¹Ø¼ü×Ö", Toast.LENGTH_SHORT).show();
		}
	}
	private Cursor queryLectureCursor(String text){
			return dbUtil.queryForCursor("select * from lecture where Lname like '%"+text+"%' order by time desc"); 
	}
	private void initData(Cursor c) {
		if(mData != null){
			mData.clear();
		}
		AllForumBO bo = null;
		if(c != null){
			while(c.moveToNext()){
				bo = new AllForumBO();
				bo.imageId = R.drawable.ic_forum;
				bo.breifTitle = c.getString(c.getColumnIndex("Lname"));
				bo.time = c.getString(c.getColumnIndex("Time"));
				mData.add(bo);
			}
		}
		if(c != null && !c.isClosed()){
			c.close();
		}
	}
}
