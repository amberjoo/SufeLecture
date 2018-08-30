package com.sufe.fragment;

import java.util.ArrayList;
import java.util.Date;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.sufe.database.DatabaseUtil;
import com.sufe.lecture.R;
import com.sufe.lecture.adapter.ToadyForumAdapter;
import com.sufe.lecture.biz.model.ToadyForumBO;
import com.sufe.util.DateUtil;

public class TodayLectureFragment extends Fragment implements OnClickListener{
	private Button btnToadyAll, btnTodayOrgs, btnToadyACde, btnTodayPre;// header_2
	private ListView lvToday;
	private ArrayList<ToadyForumBO> mData = new ArrayList<ToadyForumBO>();
	private View view;
	private DatabaseUtil dbUtil;
	private ToadyForumAdapter forumAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view= inflater.inflate(R.layout.layout_today_forum_detail, container,false);
		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		btnToadyAll = (Button) view.findViewById(R.id.btn_all);
		btnToadyAll.setOnClickListener(this);
		btnToadyACde = (Button) view.findViewById(R.id.btn_academic);
		btnToadyACde.setOnClickListener(this);
		btnTodayOrgs = (Button) view.findViewById(R.id.btn_orgs);
		btnTodayOrgs.setOnClickListener(this);
		btnTodayPre = (Button) view.findViewById(R.id.btn_preach);
		btnTodayPre.setOnClickListener(this);
		lvToday = (ListView) view.findViewById(R.id.lv_today_detail);
		btnToadyAll.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_htodayview_b1_1));

		if (dbUtil == null) {
			dbUtil = new DatabaseUtil(getActivity());
		}
		Cursor c = queryLectureCursor("all");
		initData(c);
		forumAdapter = new ToadyForumAdapter(mData, getActivity());
		lvToday.setAdapter(forumAdapter);
		
	}
	private void clearPicBg(){
		btnToadyAll.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_htodayview_b1_2));
		btnToadyACde.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_htodayview_b2_2));
		btnTodayOrgs.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_htodayview_b3_2));
		btnTodayPre.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_htodayview_b4_2));
	}

	@Override
	public void onClick(View v) {
		clearPicBg();
		switch (v.getId()) {
		case R.id.btn_all:
			btnToadyAll.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_htodayview_b1_1));
			Cursor c1 = queryLectureCursor("all");
			initData(c1);
			forumAdapter.notifyDataSetChanged();
			break;
		case R.id.btn_academic:
			btnToadyACde.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_htodayview_b2_1));
			Cursor c2 = queryLectureCursor("xs");
			initData(c2);
			forumAdapter.notifyDataSetChanged();
			break;
		case R.id.btn_orgs:
			btnTodayOrgs.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_htodayview_b3_1));
			Cursor c3 = queryLectureCursor("st");
			initData(c3);
			forumAdapter.notifyDataSetChanged();
			break;
		case R.id.btn_preach:
			btnTodayPre.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_htodayview_b4_1));
			Cursor c4 = queryLectureCursor("xjh");
			initData(c4);
			forumAdapter.notifyDataSetChanged();
			break;
		default:
			break;
		}
	}
	private void initData(Cursor c) {
		if(mData != null){
			mData.clear();
		}
		ToadyForumBO bo = null;
		if(c != null){
			while(c.moveToNext()){
				bo = new ToadyForumBO();
				bo.imageId = R.drawable.ic_poster;
				bo.breifTitle = c.getString(c.getColumnIndex("Lname"));
				bo.Lno = c.getString(c.getColumnIndex("Lno"));
				mData.add(bo);
			}
		}
		if(c != null && !c.isClosed()){
			c.close();
		}
	}
	private Cursor queryLectureCursor(String type){
		//学术1，社团2，宣讲会3
		if("all".equals(type)){
			//select * from lecture where time > ?<-->'2014-05-02'
			return dbUtil.queryForCursor("select * from lecture where time > ?",
					new String[]{DateUtil.getCurrentDate()}); 
		}
		if("xs".equals(type)){
			return dbUtil.queryForCursor("select * from lecture where time > ? and type='1'",
					new String[]{DateUtil.getCurrentDate()}); 
		}
		if("st".equals(type)){
			return dbUtil.queryForCursor("select * from lecture where time > ? and type='2'",
					new String[]{DateUtil.getCurrentDate()}); 
		}
		if("xjh".equals(type)){
			return dbUtil.queryForCursor("select * from lecture where time > ? and type='3'",
					new String[]{DateUtil.getCurrentDate()}); 
		}
		return null;
	}
}
