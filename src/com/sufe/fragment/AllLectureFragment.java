package com.sufe.fragment;

import java.util.ArrayList;

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
import com.sufe.lecture.adapter.AllForumAdapter;
import com.sufe.lecture.biz.model.AllForumBO;
import com.sufe.util.DateUtil;

public class AllLectureFragment extends Fragment implements OnClickListener{
	private View view;
	private Button btnRecent,btnPast;
	private ListView lv_all;
	private ArrayList<AllForumBO> mData = new ArrayList<AllForumBO>();
	private DatabaseUtil dbUtil;
	private AllForumAdapter adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.layout_all_forum_detail, container,false);
		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		btnRecent = (Button) view.findViewById(R.id.btn_recent);
		btnRecent.setOnClickListener(this);
		btnPast = (Button) view.findViewById(R.id.btn_past);
		btnPast.setOnClickListener(this);
		lv_all = (ListView) view.findViewById(R.id.lv_all_detail);
		btnRecent.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_hallview_left1));
		
		if (dbUtil == null) {
			dbUtil = new DatabaseUtil(getActivity());
		}
		Cursor c = queryLectureCursor("recent");
		initData(c);
		adapter = new AllForumAdapter(mData, getActivity());
		lv_all.setAdapter(adapter);
	}
	private void clearPicBg(){
		btnRecent.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_hallview_left2));
		btnPast.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_hallview_right2));
	}
	@Override
	public void onClick(View v) {
		clearPicBg();
		switch (v.getId()) {
		case R.id.btn_recent:
			btnRecent.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_hallview_left1));
			Cursor c1 = queryLectureCursor("recent");
			initData(c1);
			adapter.notifyDataSetChanged();
			break;
		case R.id.btn_past:
			btnPast.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_hallview_right));
			Cursor c2 = queryLectureCursor("past");
			initData(c2);
			adapter.notifyDataSetChanged();
			break;
		default:
			break;
		}
	}
	
	private void initData(Cursor c) {
		if(mData != null){
			mData.clear();
		}
		AllForumBO bo = null;
		if(c != null){
			while(c.moveToNext()){
				bo = new AllForumBO();
				bo.imageId = R.drawable.ic_poster2;
				bo.breifTitle = c.getString(c.getColumnIndex("Lname"));
				bo.time = c.getString(c.getColumnIndex("Time"));
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
		String date = DateUtil.getCurrentDate();
		if("recent".equals(type)){
			return dbUtil.queryForCursor("select * from lecture where time > ? order by time desc",
					new String[]{date}); 
		}
		if("past".equals(type)){
			return dbUtil.queryForCursor("select * from lecture where time < ? order by time desc",
					new String[]{date}); 
		}
		return null;
	}
}
