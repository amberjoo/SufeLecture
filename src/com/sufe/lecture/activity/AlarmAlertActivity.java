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
		.setTitle("�ƴ�������������")
		.setMessage("���ã�����ע�Ľ����ڽ��ھ��У���鿴���顣")
		.setPositiveButton("֪����",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				AlarmAlertActivity.this.finish();
			}
		})
		.show();
	}
}
