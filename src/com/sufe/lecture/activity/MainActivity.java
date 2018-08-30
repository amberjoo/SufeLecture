package com.sufe.lecture.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.sufe.fragment.AllLectureFragment;
import com.sufe.fragment.MineLectureFragment;
import com.sufe.fragment.TodayLectureFragment;
import com.sufe.lecture.R;
import com.sufe.lecture.adapter.ToadyForumAdapter;
import com.sufe.lecture.biz.model.ToadyForumBO;

public class MainActivity extends Activity implements OnClickListener{
	private Button btnToday, btnAll, btnMine, btn_header_search,btn_header_more;// header_2 button

	
	private FragmentManager fragmentManager;
	private TodayLectureFragment todayLectureFragment;
	private AllLectureFragment allLectureFragment;
	private MineLectureFragment mineLectureFragment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnToday = (Button) findViewById(R.id.tv_today_forum);
		btnAll = (Button) findViewById(R.id.tv_all_forum);
		btnMine = (Button) findViewById(R.id.tv_mine_forum);
		btn_header_search = (Button) findViewById(R.id. btn_header_search);
		btn_header_more = (Button) findViewById(R.id.btn_header_more);
		btnToday.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_main_today3));
		btnToday.setOnClickListener(this);
		btnAll.setOnClickListener(this);
		btnMine.setOnClickListener(this);
		btn_header_search.setOnClickListener(this);
		btn_header_more.setOnClickListener(this);
		fragmentManager = getFragmentManager();
		setSelection(0);
	}
	
	private void clearPicBg(){
		btnToday.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_main_today1));
		btnAll.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_main_all1));
		btnMine.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_main_mine1));
	}

	@Override
	public void onClick(View v) {
		clearPicBg();
		switch (v.getId()) {
		case R.id.tv_today_forum:
			setSelection(0);
			btnToday.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_main_today3));
			break;
		case R.id.tv_all_forum:
			setSelection(1);
			btnAll.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_main_all3));
			break;
		case R.id.tv_mine_forum:
			setSelection(2);
			btnMine.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_main_mine3));
			break;
		case R.id.btn_header_search:
			 Intent intent = new Intent(MainActivity.this,SearchActivity.class);
			 startActivity(intent);
			 break;
		case R.id.btn_header_more:
			Intent intent2 = new Intent(MainActivity.this,PersonalActivity.class);
			 startActivity(intent2);
			break;
		}
	}
	
	private void setSelection(int index){
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		hideFragment(transaction);
		switch (index) {
		case 0:
			if(todayLectureFragment == null){
				todayLectureFragment = new TodayLectureFragment();
				transaction.add(R.id.content, todayLectureFragment);
			}
			else{
				transaction.show(todayLectureFragment);
			}
			break;
		case 1:
			if (allLectureFragment == null) {
				allLectureFragment = new AllLectureFragment();
				transaction.add(R.id.content, allLectureFragment);
			} else {
				transaction.show(allLectureFragment);
			}
			break;
		case 2:
			if(mineLectureFragment == null){
				mineLectureFragment = new MineLectureFragment();
				transaction.add(R.id.content, mineLectureFragment);
			}
			else{
				transaction.show(mineLectureFragment);
			}
			break;
		default:
			break;
		}
		transaction.commit();
	}
	private void hideFragment(FragmentTransaction transaction){
		if(todayLectureFragment != null){
			transaction.hide(todayLectureFragment);
		}
		if(allLectureFragment != null){
			transaction.hide(allLectureFragment);
		}
		if(mineLectureFragment != null){
			transaction.hide(mineLectureFragment);
		}
	}
	
}
