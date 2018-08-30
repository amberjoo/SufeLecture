package com.sufe.lecture.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sufe.lecture.R;

public class LoginSelectActivity extends Activity implements
		View.OnClickListener {
	private Button RegisterButton;
	private Button LoginButton;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login_select);
		RegisterButton = (Button) findViewById(R.id.loginimgb2);
		LoginButton = (Button) findViewById(R.id.loginimgb1);
		RegisterButton.setOnClickListener(this);
		LoginButton.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.loginimgb1:
			Intent itr = new Intent(LoginSelectActivity.this,
					LoginPageActivity.class);
			startActivity(itr);
			break;
		case R.id.loginimgb2:
			Intent itl = new Intent(LoginSelectActivity.this,
					RegisterPageActivity.class);
			startActivity(itl);
			break;

		}
	}
}