package com.sufe.lecture.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import com.sufe.database.DatabaseUtil;
import com.sufe.lecture.R;

public class LogoActivity extends Activity {
	private DatabaseUtil dbUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// 取消标题
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.activity_logo);
		// 取消状态栏
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		if (dbUtil == null) {
			dbUtil = new DatabaseUtil(getApplicationContext());
		}
		ImageView iv = (ImageView) findViewById(R.id.logo1);
		AlphaAnimation aa = new AlphaAnimation(0.1f, 1.0f);
		aa.setDuration(3000);
		iv.startAnimation(aa);
		aa.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
			}

			@Override
			public void onAnimationRepeat(Animation animation) {
			}

			@Override
			public void onAnimationEnd(Animation animation) {
				judgeInit();
				Intent it = new Intent(LogoActivity.this, LoginSelectActivity.class);
				startActivity(it);
				finish();

			}
		});
	}
	private void judgeInit(){
		SharedPreferences sp = getSharedPreferences("ini",MODE_PRIVATE);
		int i = sp.getInt("hasInit", 0);
		if(i == 0){
			Editor e = sp.edit();
			e.putInt("hasInit", 1);
			e.commit();
			dealInitData();
		}
	}
	private void dealInitData() {
		Resources res = this.getResources();
		InputStream in = null;
		BufferedReader br = null;
		try {
			in = res.openRawResource(R.raw.sql);
			 br = new BufferedReader(new InputStreamReader(in, "GBK"));
			String sql = null;
			while ((sql = br.readLine()) != null) {
				Log.e("znj",sql);
				dbUtil.exeSQL(sql);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		System.out.println("-------onDestroy-------");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		System.out.println("-------onResume-------");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		System.out.println("-------onStart-------");
	}

}
