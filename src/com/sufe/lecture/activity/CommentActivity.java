package com.sufe.lecture.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sufe.application.SufeApplication;
import com.sufe.database.DatabaseUtil;
import com.sufe.lecture.R;
import com.sufe.util.DateUtil;

public class CommentActivity extends Activity implements View.OnClickListener {

	private Button btn_comment_back;
	private Button btn_comment_set;
	private EditText edt_comment_text;
	private DatabaseUtil dbUtil;
	private String sno;
	private String lno;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lecture_comment);
		btn_comment_back = (Button) findViewById(R.id.btn_comment_back);
		btn_comment_set = (Button) findViewById(R.id.btn_comment_set);
		edt_comment_text = (EditText) findViewById(R.id.edt_comment_text);

		btn_comment_back.setOnClickListener(this);
		btn_comment_set.setOnClickListener(this);
		sno = ((SufeApplication) this.getApplication()).getSno();
		lno = getIntent().getStringExtra("Lno");

		if (dbUtil == null) {
			dbUtil = new DatabaseUtil(this);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_comment_back:
			finish();
			break;
		case R.id.btn_comment_set:
			comment();
			Intent it = new Intent();
			it.putExtra("Lno", lno);
			setResult(188,it);
			finish();
			break;
		}

	}

	private void comment() {
		// TODO Auto-generated method stub
		String comment = edt_comment_text.getText().toString().trim();
		if (!comment.equals("")) {
			Cursor c = returnCommentCursor();
			int r = c.getCount();
			c.close();

			ContentValues values = new ContentValues();
			values.put("Cno", r + 1);
//			values.put("Time", DateUtil.getCurrentDate());
			values.put("Content", comment);
			values.put("Lno", lno);
			values.put("Sno", sno);
			dbUtil.insert("Comment", values);

		} else {
			Toast.makeText(this, "«Î ‰»Î¡Ù—‘", Toast.LENGTH_SHORT).show();
		}
	}

	private Cursor returnCommentCursor() {
		return dbUtil.queryForCursor("select * from Comment", null);
	}
}