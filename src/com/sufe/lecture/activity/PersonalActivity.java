package com.sufe.lecture.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sufe.application.SufeApplication;
import com.sufe.database.DatabaseUtil;
import com.sufe.lecture.R;

public class PersonalActivity extends Activity implements View.OnClickListener {
	private Button btn_personal_back;
	private Button btn_personal_edtp;
	private Button btn_personal_edte;
	private Button btn_personal_logoff;
	private Button btn_personal_exit;
	private TextView tv_personal_sno, tv_personal_sname, tv_personal_sschool,
			tv_personal_smajor;
	private TextView tv_personal_spho, tv_personal_seml;
	private String sno;
	private DatabaseUtil dbUtil;
	private String sname, school, major, pho, eml;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_info);
		btn_personal_back = (Button) findViewById(R.id.btn_personal_back);
		btn_personal_edtp = (Button) findViewById(R.id.btn_personal_edtp);
		btn_personal_edte = (Button) findViewById(R.id.btn_personal_edte);
		btn_personal_logoff = (Button) findViewById(R.id.btn_personal_logoff);
		btn_personal_exit = (Button) findViewById(R.id.btn_personal_exit);
		tv_personal_sno = (TextView) findViewById(R.id.tv_personal_sno);
		tv_personal_sname = (TextView) findViewById(R.id.tv_personal_sname);
		tv_personal_sschool = (TextView) findViewById(R.id.tv_personal_sschool);
		tv_personal_smajor = (TextView) findViewById(R.id.tv_personal_smajor);
		tv_personal_spho = (TextView) findViewById(R.id.tv_personal_spho);
		tv_personal_seml = (TextView) findViewById(R.id.tv_personal_seml);
		
		btn_personal_back.setOnClickListener(this);
		btn_personal_edtp.setOnClickListener(this);
		btn_personal_edte.setOnClickListener(this);
		btn_personal_logoff.setOnClickListener(this);
		btn_personal_exit.setOnClickListener(this);
		
		if (dbUtil == null) {
			dbUtil = new DatabaseUtil(this);
		}
		sno = ((SufeApplication) this.getApplication()).getSno();
		getData(sno);
		tv_personal_sno.setText(sno);
		tv_personal_sname.setText(sname);
		tv_personal_sschool.setText(school);
		tv_personal_smajor.setText(major);
		tv_personal_spho.setText(pho);
		tv_personal_seml.setText(eml);
	}

	private void getData(String sno) {
		Cursor c = returnStudentCursor(sno);
		if (c != null) {
			while (c.moveToNext()) {
				sname = c.getString(c.getColumnIndex("Sname"));
				school = c.getString(c.getColumnIndex("SCname"));
				major = c.getString(c.getColumnIndex("Mname"));
				pho = c.getString(c.getColumnIndex("Tel"));
				eml = c.getString(c.getColumnIndex("Email"));
			}
			c.close();
		}
	}

	private Cursor returnStudentCursor(String sno2) {
		// TODO Auto-generated method stub
		return dbUtil
				.queryForCursor(
						"select * from Student,Major,School where Sno = ? and Student.Mno=Major.Mno and School.SCno = Student.SCno",
						new String[] { sno2 });

	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_personal_back:
			finish();
			break;
		case R.id.btn_personal_edtp:

			break;
		case R.id.btn_personal_edte:

			break;

		case R.id.btn_personal_logoff:
			Intent intent = new Intent(PersonalActivity.this,
					LoginSelectActivity.class);
			startActivity(intent);
			finish();
			break;

		case R.id.btn_personal_exit:
			exit();
			break;

		}
	}

	private void exit() {
		System.exit(0);

	}

}
