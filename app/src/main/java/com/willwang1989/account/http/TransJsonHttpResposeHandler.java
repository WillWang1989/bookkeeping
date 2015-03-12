package com.willwang1989.account.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.willwang1989.asbookkeeping.R;
import com.willwang1989.account.utils.commons;

public class TransJsonHttpResposeHandler extends JsonHttpResponseHandler {
	private View c = null;
	private final String LOG_TAG = "Fragment";

	public TransJsonHttpResposeHandler(View vg) {
		this.c = vg;
	}

	public void onSuccess(int statusCode, Header[] headers, JSONObject res) {
		try {

			ListView listView = (ListView) c.findViewById(R.id.listView);

			List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
			JSONArray results = res.getJSONArray("results");
			for (int i = 0; i < results.length(); i++) {
				HashMap<String, Object> item = new HashMap<String, Object>();
				JSONObject obj = results.getJSONObject(i);
				item.put("Name", obj.getString("UserName"));
				item.put("Item", obj.getString("Item"));
				item.put("Amount", obj.getDouble("Amount"));
				item.put("CreateTime",
						commons.FormatDate(obj.getLong("CreateTime")));
				item.put("Remarks", obj.getString("Remarks"));
				data.add(item);
			}
			// 创建SimpleAdapter适配器将数据绑定到item显示控件上
			SimpleAdapter adapter = new SimpleAdapter(c.getContext(), data,
					R.layout.item, new String[] { "Name", "Item", "Amount",
							"CreateTime","Remarks" }, new int[] { R.id.txtName,
							R.id.txtList_Item, R.id.txtAmount, R.id.txtCreateTime,R.id.txtNote});
			// 实现列表的显示
			if (listView == null) {
				Log.i(LOG_TAG, "listview is null");
			} else
				listView.setAdapter(adapter);

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void onFailure(int statusCode, Header[] headers,
			Throwable throwable, JSONObject errorResponse) {
		if (throwable instanceof java.net.SocketTimeoutException) {
			Toast.makeText(c.getContext(), "连接超时", Toast.LENGTH_SHORT).show();
			return;
		}
		Log.i(LOG_TAG, "提交失败", throwable);
		Toast.makeText(c.getContext(), "提交失败", Toast.LENGTH_SHORT).show();
	}

}
