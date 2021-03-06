package com.sufe.lecture.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sufe.lecture.R;
import com.sufe.lecture.activity.InformationActivity;
import com.sufe.lecture.biz.model.AllForumBO;
import com.sufe.lecture.biz.model.ToadyForumBO;

public class AllForumAdapter extends BaseAdapter {
	private ArrayList<AllForumBO> mData;
	private Context mContext;
	private LayoutInflater mInflater;
	public AllForumAdapter(ArrayList<AllForumBO> mData,Context mContext){
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
			convertView = mInflater.inflate(R.layout.all_forum_item, null);
			holder = new ViewHolder();
			holder.imgForum = (ImageView) convertView.findViewById(R.id.img_title_one);
			holder.tvBreifTitle = (TextView) convertView.findViewById(R.id.tv_forum_breif);
			holder.tvTime = (TextView) convertView.findViewById(R.id.tv_time);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		final AllForumBO bo = mData.get(arg0);
		holder.imgForum.setBackgroundResource(bo.imageId);
		holder.tvBreifTitle.setText(bo.breifTitle);
		holder.tvTime.setText(bo.time());
		holder.tvBreifTitle.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
//				Toast.makeText(mContext, "������:"+bo.breifTitle+"", 100).show();
				Intent intent = new Intent(mContext,InformationActivity.class);
				intent.putExtra("Lno", bo.Lno);
				mContext.startActivity(intent);
			}
		});
		
		return convertView;
	}
	static class ViewHolder{
		public ImageView imgForum;
		public TextView tvBreifTitle;
		public TextView tvTime;
	}
}
