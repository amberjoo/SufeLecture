package com.sufe.lecture.activity;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.sufe.database.DatabaseUtil;
import com.sufe.lecture.R;
import com.sufe.receiver.CallAlertReceiver;

public class AlertActivity extends Activity implements View.OnClickListener {
	private Button btn_alert_setting, btn_alert_dsetting, btn_alert_cancel,
			btn_alert_back;
	private TextView tv_current_time, tv_alert_date2;
	private TextView tv_alert_lname2, tv_alert_ltime2;
	private String lno;
	private String name, time;
	private DatabaseUtil dbUtil;

	Calendar c = Calendar.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alert);
		btn_alert_setting = (Button) findViewById(R.id.btn_alert_setting);
		btn_alert_cancel = (Button) findViewById(R.id.btn_alert_cancel);
		btn_alert_back = (Button) findViewById(R.id.btn_alert_back);
		tv_current_time = (TextView) findViewById(R.id.tv_current_Time);
		tv_alert_date2 = (TextView) findViewById(R.id.tv_alert_date2);
		tv_alert_lname2 = (TextView) findViewById(R.id.tv_alert_lname2);
		tv_alert_ltime2 = (TextView) findViewById(R.id.tv_alert_ltime2);

		lno = getIntent().getStringExtra("Lno");

		if (dbUtil == null) {
			dbUtil = new DatabaseUtil(this);
		}
		
		btn_alert_setting.setOnClickListener(this);
		btn_alert_back.setOnClickListener(this);
		getData(lno);
		tv_alert_lname2.setText(name);
		tv_alert_ltime2.setText(time);

	}

	private void getData(String lno2) {
		// TODO Auto-generated method stub
		Cursor sc = returnCursor(lno);
		if (sc != null) {
			while (sc.moveToNext()) {
				name = sc.getString(sc.getColumnIndex("Lname"));
				time = sc.getString(sc.getColumnIndex("Time"));
			}
			sc.close();
		}
	}

	private Cursor returnCursor(String lno2) {
		// TODO Auto-generated method stub
		return dbUtil.queryForCursor("select * from lecture where Lno = ?",
				new String[] { lno });
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_alert_setting:
			// 初始化显示时间
			c.setTimeInMillis(System.currentTimeMillis());
			int mHour = c.get(Calendar.HOUR_OF_DAY);
			int mMinute = c.get(Calendar.MINUTE);

			new TimePickerDialog(AlertActivity.this,
					new TimePickerDialog.OnTimeSetListener() {
						@Override
						public void onTimeSet(TimePicker view, int hourOfDay,
								int minute) {
							// TODO Auto-generated method stub
							c.setTimeInMillis(System.currentTimeMillis());
							c.set(Calendar.HOUR_OF_DAY, hourOfDay);
							c.set(Calendar.MINUTE, minute);
							c.set(Calendar.SECOND, 0);
							c.set(Calendar.MILLISECOND, 0);

							Intent intent = new Intent(AlertActivity.this,
									CallAlertReceiver.class);
							PendingIntent sender = PendingIntent.getBroadcast(
									AlertActivity.this, 0, intent, 0);

							AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
							am.set(AlarmManager.RTC_WAKEUP,
									c.getTimeInMillis(), sender);

							String tmpS = format(hourOfDay) + "时"
									+ format(minute) + "分";
							tv_current_time.setText(tmpS);
							Toast.makeText(AlertActivity.this,
									"设置闹钟时间为" + tmpS, Toast.LENGTH_SHORT)
									.show();

						}

					}, mHour, mMinute, true).show();
			break;
		case R.id.btn_alert_back:

			/*
			 * c.setTimeInMillis(System.currentTimeMillis()); int mDate =
			 * c.get(Calendar.DAY_OF_MONTH); int mMonth = c.get(Calendar.MONTH);
			 * int mYear = c.get(Calendar.YEAR);
			 * 
			 * new DatePickerDialog(AlertActivity.this, new
			 * DatePickerDialog.OnDateSetListener() {
			 * 
			 * @Override public void onDateSet(DatePicker view, int year, int
			 * monthOfYear, int dayOfMonth) { // TODO Auto-generated method stub
			 * c.setTime(System.currentTimeMillis()); c.set(Calendar.YEAR,
			 * year); c.set(Calendar.MONTH,monthOfYear);
			 * c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			 * 
			 * Intent intent = new Intent(AlertActivity.this,
			 * CallAlertReceiver.class); } };
			 * 
			 * , mYear, mMonth, mDate); D
			 * 
			 * @Override public void onTimeSet(TimePicker view, int hourOfDay,
			 * int minute) { // TODO Auto-generated method stub
			 * 
			 * Intent intent = new Intent(AlertActivity.this,
			 * CallAlertReceiver.class); PendingIntent sender =
			 * PendingIntent.getBroadcast( AlertActivity.this, 0, intent, 0);
			 * 
			 * AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
			 * am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);
			 * 
			 * String tmpS = format(hourOfDay) + "时" + format(minute) + "分";
			 * tv_current_time.setText(tmpS); Toast.makeText(AlertActivity.this,
			 * "设置闹钟时间为" + tmpS, Toast.LENGTH_SHORT) .show();
			 * 
			 * }
			 * 
			 * }, mHour, mMinute, true).show();
			 */
			finish();
			break;
		case R.id.btn_alert_cancel:
			Intent intent = new Intent(AlertActivity.this,AlarmAlertActivity.class);
			PendingIntent sender = PendingIntent.getBroadcast(this,0,intent,0);
			AlarmManager am;
			am = (AlarmManager)getSystemService(ALARM_SERVICE);
			am.cancel(sender);
			Toast.makeText(AlertActivity.this, "删除提醒成功", Toast.LENGTH_SHORT).show();
			tv_current_time.setText("未设置提醒");
			break;
		}
	}

	private String format(int x) {
		String s = "" + x;
		if (s.length() == 1)
			s = "0" + s;
		return s;
	}

};
