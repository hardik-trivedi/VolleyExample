package com.example.volleyexample;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity {
	public final String TAG = getClass().getSimpleName();
	private ArrayList<Blog> blogList;
	private ListView listView;
	private BlogAdapter ba;
	private ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		blogList = new ArrayList<Blog>();
		listView = (ListView) findViewById(R.id.listView);
		ba = new BlogAdapter(this, R.layout.blog_item, blogList);

		listView.setAdapter(ba);
		RequestQueue reqQueue = Volley.newRequestQueue(this);
		String url = null;
		try {
			url = "https://ajax.googleapis.com/ajax/services/feed/find?v=1.0&q="
					+ URLEncoder.encode("Official Google Blogs", "utf-8");

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url,
				null, new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						// TODO Auto-generated method stub
						parseJSONRespone(response);
						ba.notifyDataSetChanged();
						progress.dismiss();
					}

				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						// TODO Auto-generated method stub
						Log.v(TAG, error.getMessage());
					}
				});

		progress = ProgressDialog.show(this, "",
				"Loading google's official blogs");
		reqQueue.add(jr);
	}

	private void parseJSONRespone(JSONObject response) {
		// TODO Auto-generated method stub
		try {
			JSONObject responseData = response.getJSONObject("responseData");
			JSONArray entries = responseData.getJSONArray("entries");

			for (int count = 0; count < entries.length(); count++) {

				JSONObject anEntry = entries.getJSONObject(count);
				Blog aBlog = new Blog(anEntry.optString("title"),
						anEntry.optString("url"));
				blogList.add(aBlog);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
