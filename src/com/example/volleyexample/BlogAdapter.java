package com.example.volleyexample;

import java.util.ArrayList;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class BlogAdapter extends BaseAdapter {

	private ArrayList<Blog> blogList;
	private int layout;
	private Context context;

	public BlogAdapter(Context context, int layout, ArrayList<Blog> blogList) {
		this.context = context;
		this.blogList = blogList;
		this.layout = layout;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return blogList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = ((LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(layout, null);
		}

		Blog aBlog = blogList.get(position);

		TextView txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
		TextView txtUrl = (TextView) convertView.findViewById(R.id.txtUrl);

		txtTitle.setText(Html.fromHtml(aBlog.getTitle()));
		txtUrl.setText(aBlog.getUrl());

		return convertView;
	}
}
