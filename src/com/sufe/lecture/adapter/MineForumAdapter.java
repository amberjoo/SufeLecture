package com.sufe.lecture.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sufe.lecture.R;
import com.sufe.lecture.activity.AlertActivity;
import com.sufe.lecture.activity.InformationActivity;
import com.sufe.lecture.biz.model.MineForumBO;

public class MineForumAdapter extends BaseAdapter {
	private ArrayList<MineForumBO> mData;
	private Context mContext;
	private LayoutInflater mInflater;
	public MineForumAdapter(ArrayList<MineForumBO> mData,Context mContext){
		this.mContext = mContext;
		this.mData = mData;
		mInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int arg0) {
		return mData.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		ViewHolder holder = null;
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.mine_forum_item, null);
			holder = new ViewHolder();
			holder.btnCancel = (Button) convertView.findViewById(R.id.btn_cancel);
			holder.tvBreifTitle = (TextView) convertView.findViewById(R.id.tv_forum_breif);
			holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
			holder.btnTip = (Button) convertView.findViewById(R.id.btn_tip);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		final MineForumBO bo = mData.get(arg0);
		holder.tvBreifTitle.setText(bo.breifTitle);
		holder.tvTime.setText(bo.time());
		if("1".equals(bo.isGiveup)){
			holder.btnCancel.setTag("1");//已放弃
			holder.btnCancel.setBackgroundColor(mContext.getResources().getColor(R.color.gray_bg));
		}
		else{
			holder.btnCancel.setTag("0");//没有放弃参加，可以点放弃
			holder.btnCancel.setBackgroundColor(mContext.getResources().getColor(R.color.red));
		}
		if("1".equals(bo.isTip)){
			holder.btnTip.setTag("1");//已设置了提醒
			holder.btnTip.setBackgroundColor(mContext.getResources().getColor(R.color.gray_bg));
		}
		else{
			holder.btnTip.setTag("0");//未设置了提醒
			holder.btnTip.setBackgroundColor(mContext.getResources().getColor(R.color.skyblue));
		}
		holder.tvBreifTitle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
//				Toast.makeText(mContext, "你点击了:"+bo.breifTitle+"", 100).show();
				Intent intent = new Intent(mContext,InformationActivity.class);
				intent.putExtra("Lno", bo.Lno);
				mContext.startActivity(intent);
			}
		});
		final String tag_cancel = (String) holder.btnCancel.getTag();
		holder.btnCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if("0".equals(tag_cancel)){
					Toast.makeText(mContext, "你点击了:"+bo.Lno+",可以放弃参加！", 100).show();
				}
			}
		});
		final String tag_tip = (String) holder.btnTip.getTag();
		holder.btnTip.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if("0".equals(tag_tip)){
//					Toast.makeText(mContext, "你点击了:"+bo.Lno+",可以设置提醒！", 100).show();
					Intent intent = new Intent(mContext,AlertActivity.class);
					intent.putExtra("Lno", bo.Lno);
					mContext.startActivity(intent);
				}
			}
		});
		return convertView;
	}
	static class ViewHolder{
		public TextView tvBreifTitle;
		public TextView tvTime;
		public Button btnTip;
		public Button btnCancel;
	}
}
