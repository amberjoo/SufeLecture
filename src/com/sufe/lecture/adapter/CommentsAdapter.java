package com.sufe.lecture.adapter;

import java.util.ArrayList;

import com.sufe.lecture.R;
import com.sufe.lecture.adapter.AllForumAdapter.ViewHolder;
import com.sufe.lecture.biz.model.AllForumBO;
import com.sufe.lecture.biz.model.CommentBO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CommentsAdapter extends BaseAdapter {

	private ArrayList<CommentBO> mData;
	private Context mContext;
	private LayoutInflater mInflater;
	public CommentsAdapter(ArrayList<CommentBO> mData,Context mContext){
		this.mContext = mContext;
		this.mData = mData;
		mInflater = LayoutInflater.from(mContext);
	}


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return  mData.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0,  View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if(convertView == null){
			convertView = mInflater.inflate(R.layout.info_comment_item, null);
			holder = new ViewHolder();
			holder.tvSName = (TextView) convertView.findViewById(R.id.tv_comment_name);
			holder.tvTime = (TextView) convertView.findViewById(R.id.tv_comment_time);
			holder.tvContent = (TextView) convertView.findViewById(R.id.tv_comment_breif);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		final CommentBO bo = mData.get(arg0);
		holder.tvSName.setText(bo.Sno);
		holder.tvTime.setText(bo.time());
		holder.tvContent.setText(bo.Content);
		
		return convertView;
	}
	static class ViewHolder{
		public TextView tvSName;
		public TextView tvTime;
		public TextView tvContent;
	}
}
