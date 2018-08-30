package com.sufe.lecture.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.sufe.application.SufeApplication;
import com.sufe.database.DatabaseUtil;
import com.sufe.lecture.R;

public class RegisterPageActivity extends Activity implements
		View.OnClickListener {
	private Button btn_back_rg;
	private Button btn_header_rg;
	private EditText edt_rgview_pho;
	private EditText edt_rgview_eml;
	private EditText edt_rgview_rpwd;
	private EditText edt_rgview_rrpwd;
	private EditText edt_rgview_acc;
	private EditText edt_rgview_name;
	private Spinner spr_rgview_scl;
	private Spinner spr_rgview_mar;
	private DatabaseUtil dbUtil;
	private String schoolName = "";
	private String SCNo;
	private String MjNo;
	private ArrayAdapter<String> major_adapter;
	private ArrayAdapter<String> school_adapter;
	private ArrayList<String> major = new ArrayList<String>();
	private ArrayList<String> majorNo = new ArrayList<String>();
	private ArrayList<String> school;
	private ArrayList<String> schoolNo;
	private Button img_rgview_ccl, img_rgview_ccl2, img_rgview_ccl3,
	img_rgview_ccl4, img_rgview_ccl5, img_rgview_ccl6;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register_page);

		btn_back_rg = (Button) findViewById(R.id.btn_back_rg);
		btn_header_rg = (Button) findViewById(R.id.btn_header_rg);
		edt_rgview_pho = (EditText) findViewById(R.id.edt_rgview_pho);
		edt_rgview_eml = (EditText) findViewById(R.id.edt_rgview_eml);
		edt_rgview_rpwd = (EditText) findViewById(R.id.edt_rgview_rpwd);
		edt_rgview_rrpwd = (EditText) findViewById(R.id.edt_rgview_rrpwd);
		edt_rgview_acc = (EditText) findViewById(R.id.edt_rgview_acc);
		edt_rgview_name = (EditText) findViewById(R.id.edt_rgview_name);
		spr_rgview_scl = (Spinner) findViewById(R.id.spr_rgview_scl);
		spr_rgview_mar = (Spinner) findViewById(R.id.spr_rgview_mar);

		img_rgview_ccl = (Button) findViewById(R.id.img_rgview_ccl);
		img_rgview_ccl2 = (Button) findViewById(R.id.img_rgview_ccl2);
		img_rgview_ccl3 = (Button) findViewById(R.id.img_rgview_ccl3);
		img_rgview_ccl4 = (Button) findViewById(R.id.img_rgview_ccl4);
		img_rgview_ccl5 = (Button) findViewById(R.id.img_rgview_ccl5);
		img_rgview_ccl6 = (Button) findViewById(R.id.img_rgview_ccl6);
		
		btn_back_rg.setOnClickListener(this);
		btn_header_rg.setOnClickListener(this);
		
		img_rgview_ccl.setOnClickListener(this);
		img_rgview_ccl2.setOnClickListener(this);
		img_rgview_ccl3.setOnClickListener(this);
		img_rgview_ccl4.setOnClickListener(this);
		img_rgview_ccl5.setOnClickListener(this);
		img_rgview_ccl6.setOnClickListener(this);

		if (dbUtil == null) {
			dbUtil = new DatabaseUtil(this);
		}
		getSchoolData();
		school_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, school);
		school_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		spr_rgview_scl.setAdapter(school_adapter);
		
		spr_rgview_scl.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				schoolName = school.get(position);
				SCNo = schoolNo.get(position);
				getMajorData();
				major_adapter.notifyDataSetChanged();
				spr_rgview_mar.setSelection(0);
				Log.e("znj", "znj-->"+schoolName);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Log.e("znj", "znj");
			}
		});
		major_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, major);
		major_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spr_rgview_mar.setAdapter(major_adapter);
		spr_rgview_mar.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				MjNo = majorNo.get(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_back_rg:
			// Intent it = new Intent(RegisterPageActivity.this,
			// LoginSelectActivity.class);
			// startActivity(it);
			finish();
			break;
		case R.id.btn_header_rg:
			register();
			break;
		case R.id.img_rgview_ccl:
			cancel();
			break;
		case R.id.img_rgview_ccl2:
			cancel2();
			break;
		case R.id.img_rgview_ccl3:
			cancel3();
			break;
		case R.id.img_rgview_ccl4:
			cancel4();
			break;
		case R.id.img_rgview_ccl5:
			cancel5();
			break;
		case R.id.img_rgview_ccl6:
			cancel6();
			break;
		}
	}
	private void cancel() {
		// TODO Auto-generated method stub
		edt_rgview_pho.setText("");

	}

	private void cancel2() {
		// TODO Auto-generated method stub
		edt_rgview_eml.setText("");

	}

	private void cancel3() {
		// TODO Auto-generated method stub
		edt_rgview_rpwd.setText("");

	}

	private void cancel4() {
		// TODO Auto-generated method stub
		edt_rgview_rrpwd.setText("");

	}

	private void cancel5() {
		// TODO Auto-generated method stub
		edt_rgview_acc.setText("");

	}

	private void cancel6() {
		// TODO Auto-generated method stub
		edt_rgview_name .setText("");

	}
	private void register() {
		// TODO Auto-generated method stub
		String phone = edt_rgview_pho.getText().toString().trim();
		String email = edt_rgview_eml.getText().toString().trim();
		String pwd = edt_rgview_rpwd.getText().toString().trim();
		String rpwd = edt_rgview_rrpwd.getText().toString().trim();
		String sno = edt_rgview_acc.getText().toString().trim();
		String sname = edt_rgview_name.getText().toString().trim();
		String school = SCNo;//spr_rgview_scl.getSelectedItem().toString().trim();
		String major = MjNo;//spr_rgview_mar.getSelectedItem().toString().trim();

		if (pwd.equals(rpwd)) {
			ContentValues values = new ContentValues();
			values.put("Sno", sno);
			values.put("Sname", sname);
			values.put("Password", pwd);
			values.put("Tel", phone);
			values.put("Email", email);
			values.put("Mno", major);
			values.put("SCno", school);

			dbUtil.insert("student", values);
			((SufeApplication) getApplication()).setSno(sno);
			Intent intent = new Intent(RegisterPageActivity.this,
					MainActivity.class);
			startActivity(intent);
			finish();
		} else {
			Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
		}

	}

	private void getSchoolData() {
		Cursor c = returnSchoolCursor();
		if (c != null) {
			school = new ArrayList<String>();
			schoolNo = new ArrayList<String>();
			while (c.moveToNext()) {
				school.add(c.getString(c.getColumnIndex("SCname")));
				schoolNo.add(c.getString(c.getColumnIndex("SCno")));
			}
			c.close();
		}
	}

	public Cursor returnSchoolCursor() {
		return dbUtil.queryForCursor("select * from school");
	}

	private void getMajorData() {
		major.clear();
		majorNo.clear();
		Cursor sc = returnMajorCursor(SCNo);
		if (sc != null) {
			while (sc.moveToNext()) {
				major.add(sc.getString(sc.getColumnIndex("Mname")));
				majorNo.add(sc.getString(sc.getColumnIndex("SCno")));
			}
			sc.close();
		}
	}

	public Cursor returnMajorCursor(String SCNo) {
		return dbUtil.queryForCursor("select * from Major where SCno = ? ",
				new String[] { SCNo });
	}

}
