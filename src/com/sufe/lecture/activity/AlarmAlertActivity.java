package com.sufe.lecture.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class AlarmAlertActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		new AlertDialog.Builder(AlarmAlertActivity.this)
		.setTitle("财大讲座：讲座提醒")
		.setMessage("您好，您关注的讲座在近期举行，请查看详情。")
		.setPositiveButton("知道了",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				AlarmAlertActivity.this.finish();
			}
		})
		.show();
	}
}
