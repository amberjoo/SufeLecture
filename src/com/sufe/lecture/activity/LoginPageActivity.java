package com.sufe.lecture.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sufe.application.SufeApplication;
import com.sufe.database.DatabaseUtil;
import com.sufe.lecture.R;

public class LoginPageActivity extends Activity implements View.OnClickListener {

	private Button BtnLogin;
	private Button BtnBack;
	private Button img_view_ccl, img_view_ccl2;

	private EditText EdtAcc;
	private EditText EdtPwd;
	private TextView TVLogin;
	private DatabaseUtil dbUtil;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_page);
		BtnLogin = (Button) findViewById(R.id.btn_header_login);
		BtnBack = (Button) findViewById(R.id.btn_header_back);
		EdtAcc = (EditText) findViewById(R.id.edt_view_acc);
		EdtPwd = (EditText) findViewById(R.id.edt_view_pwd);
		TVLogin = (TextView) findViewById(R.id.login_rgr);
		img_view_ccl = (Button) findViewById(R.id.img_view_ccl);
		img_view_ccl2 = (Button) findViewById(R.id.img_view_ccl2);
		
		BtnBack.setOnClickListener(this);
		BtnLogin.setOnClickListener(this);
		TVLogin.setOnClickListener(this);
		img_view_ccl.setOnClickListener(this);
		img_view_ccl2.setOnClickListener(this);

		if (dbUtil == null) {
			dbUtil = new DatabaseUtil(this);
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_header_back:
//			Intent it = new Intent(LoginPageActivity.this, LoginSelectActivity.class);
//			startActivity(it);
			finish();
			break;
		case R.id.login_rgr:
			Intent it = new Intent(LoginPageActivity.this, RegisterPageActivity.class);
			startActivity(it);
			break;
		case R.id.btn_header_login:
			login();
			break;
		case R.id.img_view_ccl:
			cancel1();
			break;
		case R.id.img_view_ccl2:
			cancel2();
			break;
		}
	}
	private void cancel2() {
		// TODO Auto-generated method stub
		EdtPwd.setText("");
	}

	private void cancel1() {
		// TODO Auto-generated method stub
		EdtAcc.setText("");

	}
	public void login() {
		if (isUserNameAndPwdValid()) {
			String Sno = EdtAcc.getText().toString().trim();
			String pwd = EdtPwd.getText().toString().trim();
			Cursor c = dbUtil.queryForCursor("select * from Student where Sno=? and Password=?", 
					new String[]{Sno,pwd});
			int r = c.getCount();
			if (r > 0) {
				((SufeApplication)getApplication()).setSno(Sno);
				Intent intent = new Intent();
				intent.setClass(getApplicationContext(), MainActivity.class);
				startActivity(intent);
				this.finish();
			} else if (r == 0) {
				// login failed,user does't exist
				Toast.makeText(this, getString(R.string.login_fail),
						Toast.LENGTH_SHORT).show();
			}
			if(c != null && !c.isClosed()){
				c.close();
			}
		}
	}

	public boolean isUserNameAndPwdValid() {
		if (EdtAcc.getText().toString().trim().equals("")) {
			Toast.makeText(this, getString(R.string.account_empty),
					Toast.LENGTH_SHORT).show();
			return false;
		} else if (EdtPwd.getText().toString().trim().equals("")) {
			Toast.makeText(this, getString(R.string.pwd_empty),
					Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

}
