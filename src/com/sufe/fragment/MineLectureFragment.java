package com.sufe.fragment;

import java.util.ArrayList;

import com.sufe.application.SufeApplication;
import com.sufe.database.DatabaseUtil;
import com.sufe.lecture.R;
import com.sufe.lecture.adapter.AllForumAdapter;
import com.sufe.lecture.adapter.MineForumAdapter;
import com.sufe.lecture.biz.model.AllForumBO;
import com.sufe.lecture.biz.model.MineForumBO;
import com.sufe.util.DateUtil;

import android.app.Fragment;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

public class MineLectureFragment extends Fragment implements OnClickListener{
	private View view;
	private Button btnJoinin,btnTake;
	private ListView lv_mine;
	private ArrayList<MineForumBO> mData = new ArrayList<MineForumBO>();
	private DatabaseUtil dbUtil;
	private MineForumAdapter adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.layout_mine_forum_detail, container,false);
		return view;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		btnJoinin = (Button) view.findViewById(R.id.btn_joinin);
		btnJoinin.setOnClickListener(this);
		btnTake = (Button) view.findViewById(R.id.btn_take);
		btnTake.setOnClickListener(this);
		lv_mine = (ListView) view.findViewById(R.id.lv_mine_detail);
		btnJoinin.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_hmineview_left1));

		if (dbUtil == null) {
			dbUtil = new DatabaseUtil(getActivity());
		}
		Cursor c = queryLectureCursor("joinin");
		initData(c);
		adapter = new MineForumAdapter(mData, getActivity());
		lv_mine.setAdapter(adapter);
	}
	
	private void clearPicBg(){
		btnJoinin.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_hmineview_left2));
		btnTake.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_hmineview_right2));
	}
	
	@Override
	public void onClick(View v) {
		clearPicBg();
		switch (v.getId()) {
		case R.id.btn_joinin:
			btnJoinin.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_hmineview_left1));
			Cursor c1 = queryLectureCursor("joinin");
			initData(c1);
			adapter.notifyDataSetChanged();
			break;
		case R.id.btn_take:
			btnTake.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_hmineview_right1));
			Cursor c2 = queryLectureCursor("mytake");
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
		MineForumBO bo = null;
		if(c != null){
			while(c.moveToNext()){
				bo = new MineForumBO();
				bo.imageId = R.drawable.ic_forum;
				bo.breifTitle = c.getString(c.getColumnIndex("Lname"));
				bo.time = c.getString(c.getColumnIndex("Time"));
				bo.Lno = c.getString(c.getColumnIndex("Lno"));
				bo.isTip = c.getString(c.getColumnIndex("Tip"));
				bo.isGiveup = c.getString(c.getColumnIndex("Giveup"));
				mData.add(bo);
			}
		}
		if(c != null && !c.isClosed()){
			c.close();
		}
	}
	private Cursor queryLectureCursor(String type){
		if("mytake".equals(type)){//我的参与
			String sql = "select c.Lname Lname,c.Lno Lno,c.Time Time,c.HolderStatus Giveup,d.Status Tip from (";
//			sql += " select * from Participate a, Lecture b";
			sql += " select * from PlaceHolder a, Lecture b";
			sql += " where a.Lno=b.Lno and a.Sno=? and a.HolderStatus='0'";
			sql += " ) c left join Alert d on c.Ano=d.Ano order by c.Time";
			String sno = ((SufeApplication)getActivity().getApplication()).getSno();
			return dbUtil.queryForCursor(sql,
					new String[]{sno}); 
		}
		if("joinin".equals(type)){//我的抢座
			String sql = "select c.Lname Lname,c.Lno Lno,c.Time Time,c.Pstatus Giveup,d.Status Tip from (";
//			sql += " select * from PlaceHolder a, Lecture b";
			sql += " select * from Participate a, Lecture b";
			sql += " where a.Lno=b.Lno and a.Sno=? and a.Pstatus='0'";
			sql += " ) c left join Alert d on c.Ano=d.Ano order by c.Time";
			String sno = ((SufeApplication)getActivity().getApplication()).getSno();
			return dbUtil.queryForCursor(sql,
					new String[]{sno}); 
		}
		return null;
	}
}
