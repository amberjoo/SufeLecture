package com.sufe.lecture.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import org.w3c.dom.Comment;

import com.sufe.application.SufeApplication;
import com.sufe.database.DatabaseUtil;
import com.sufe.lecture.R;
import com.sufe.lecture.adapter.AllForumAdapter;
import com.sufe.lecture.adapter.CommentsAdapter;
import com.sufe.lecture.biz.model.AllForumBO;
import com.sufe.lecture.biz.model.CommentBO;
import com.sufe.util.DateUtil;

public class InformationActivity extends Activity implements
		View.OnClickListener {

	private Button btn_info_comment, btn_info_participate,
			btn_info_placeholder;
	private ImageView img_info_poster;
	private TextView tv_info_lname, tv_info_lector, tv_info_time,
			tv_info_place, tv_info_parbox, tv_info_placebox;
	private ListView lv_info_comdetail;

	private String lno;
	private String name, lector, time, place;

	private DatabaseUtil dbUtil;

	private ArrayList<CommentBO> mData = new ArrayList<CommentBO>();
	private CommentsAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lecture_info);
		btn_info_comment = (Button) findViewById(R.id.btn_info_comment);
		btn_info_participate = (Button) findViewById(R.id.btn_info_participate);
		btn_info_placeholder = (Button) findViewById(R.id.btn_info_placeholder);
		img_info_poster = (ImageView) findViewById(R.id.img_info_poster);
		tv_info_lname = (TextView) findViewById(R.id.tv_info_lname);
		tv_info_lector = (TextView) findViewById(R.id.tv_info_lector);
		tv_info_time = (TextView) findViewById(R.id.tv_info_time);
		tv_info_place = (TextView) findViewById(R.id.tv_info_place);
		tv_info_parbox = (TextView) findViewById(R.id.tv_info_parbox);
		tv_info_placebox = (TextView) findViewById(R.id.tv_info_placebox);
		lv_info_comdetail = (ListView) findViewById(R.id.lv_info_comdetail);

		lno = getIntent().getStringExtra("Lno");
		Log.e("znj", "Lno->>" + lno);

		btn_info_participate.setOnClickListener(this);
		btn_info_placeholder.setOnClickListener(this);
		btn_info_comment.setOnClickListener(this);

		if (dbUtil == null) {
			dbUtil = new DatabaseUtil(this);
		}
		getData(lno);
		tv_info_lname.setText(name);
		tv_info_lector.setText( lector);
		tv_info_time.setText( time);
		tv_info_place.setText(place);
		setImgAccordingURl("L_1.png");

		Cursor c = returnLectureCursor(lno);
		initData(c);
		adapter = new CommentsAdapter(mData, this);
		lv_info_comdetail.setAdapter(adapter);

	}

	private void getData(String lno) {
		Cursor c = returnCursor(lno);
		if (c != null) {
			while (c.moveToNext()) {
				name = c.getString(c.getColumnIndex("Lname"));
				lector = c.getString(c.getColumnIndex("Lector"));
				time = c.getString(c.getColumnIndex("Time"));
				place = c.getString(c.getColumnIndex("Place"));
			}
			c.close();
		}
	}

	private void setImgAccordingURl(String url) {
		if ("L_1.png".equals(url)) {
			img_info_poster.setBackgroundResource(R.drawable.login_bg2);
		} else if ("L_2.png".equals(url)) {

		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_info_participate:
			if (tv_info_parbox.getVisibility() == View.VISIBLE) {
				tv_info_parbox.setVisibility(View.GONE);
			} else {
				if (tv_info_placebox.getVisibility() == View.VISIBLE) {
					tv_info_placebox.setVisibility(View.GONE);
				}
				tv_info_parbox.setVisibility(View.VISIBLE);
				String name = "×¿ÁéöÎ£¬ÀîÊàÃÉ£¬³ÂÀöÓî£¬ÖìÄþ¾ê ...";
				tv_info_parbox.setText(name);
				insertParticipate();
			}
			break;
		case R.id.btn_info_placeholder:
			if (tv_info_placebox.getVisibility() == View.VISIBLE) {
				tv_info_placebox.setVisibility(View.GONE);
			} else {
				if (tv_info_parbox.getVisibility() == View.VISIBLE) {
					tv_info_parbox.setVisibility(View.GONE);
				}
				tv_info_placebox.setVisibility(View.VISIBLE);
				String name2 = "Íõ³¬£¬³Â½Ý£¬ÕÅµ¤Äþ£¬ÖìÄþ¾ê ...";
				tv_info_placebox.setText(name2);
				insertHoldPlace();
			}
			break;
		case R.id.btn_info_comment:
			Intent intent = new Intent(InformationActivity.this,
					CommentActivity.class);
			// startActivity(intent);
			intent.putExtra("Lno", lno);
			startActivityForResult(intent, 100);
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 188) {
			Cursor c = returnLectureCursor(lno);
			initData(c);
			adapter.notifyDataSetChanged();
		}
	}

	public Cursor returnCursor(String lno) {
		return dbUtil.queryForCursor("select * from lecture where Lno = ?",
				new String[] { lno });
	}

	private void initData(Cursor c) {
		if (mData != null) {
			mData.clear();
		}
		CommentBO bo = null;
		if (c != null) {
			while (c.moveToNext()) {
				bo = new CommentBO();
				bo.Sno = c.getString(c.getColumnIndex("Sno"));
				bo.time = c.getString(c.getColumnIndex("Time"));
				bo.Content = c.getString(c.getColumnIndex("Content"));
				mData.add(bo);
			}
		}
		if (c != null && !c.isClosed()) {
			c.close();
		}
	}

	private Cursor returnLectureCursor(String lno) {
		return dbUtil.queryForCursor(
				"select * from Comment where Lno =? order by time desc",
				new String[] { lno });
	}
	private void insertHoldPlace(){
		Cursor c = dbUtil.queryForCursor("select * from PlaceHolder where Lno=?",new String[]{lno});
		if(c == null){
			return;
		}
		int cnt = c.getCount();
		if(cnt != 0){
			c.close();
			return;
		}
		c.close();
		ContentValues values = new ContentValues();
		values.put("HolderStatus", "0");
		values.put("Lno", lno);
		values.put("Sno", ((SufeApplication)getApplication()).getSno());
		dbUtil.insert("PlaceHolder", values);
	}
	private void insertParticipate(){
		Cursor c = dbUtil.queryForCursor("select * from Participate where Lno=?",new String[]{lno});
		if(c == null){
			return;
		}
		int cnt = c.getCount();
		if(cnt != 0){
			c.close();
			return;
		}
		c.close();
		ContentValues values = new ContentValues();
		values.put("Pstatus", "0");
		values.put("Lno", lno);
		values.put("Sno", ((SufeApplication)getApplication()).getSno());
		dbUtil.insert("Participate", values);
	}
}
