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
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.willwang1989.asbookkeeping.ExpandableListAdapter;
import com.willwang1989.asbookkeeping.R;
import com.willwang1989.account.bean.MonthlyReport;

public class MonthlyReportJsonHttpResponseHandler extends JsonHttpResponseHandler{
    private View v = null;
    private final String LOG_TAG = "Fragment";
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<MonthlyReport>> listDataChild;
    public MonthlyReportJsonHttpResponseHandler(View vg) {
        this.v = vg;
    }

    public void onSuccess(int statusCode, Header[] headers, JSONObject res) {
        try {

            // get the listview
            expListView = (ExpandableListView)v.findViewById(R.id.lvExp);

            // preparing list data
            listDataHeader = new ArrayList<String>();
            listDataChild = new HashMap<String,List<MonthlyReport>>();
            JSONArray data = res.getJSONArray("data");
            for(int i=0;i<data.length();i++){
                JSONObject obj = data.getJSONObject(i);
                String title = obj.getString("title");
                listDataHeader.add(title);
                JSONArray item = obj.getJSONArray("item");
                List<MonthlyReport> ls = new ArrayList<MonthlyReport>();
                for(int j =0;j<item.length();j++){
                    JSONObject sbObj = item.getJSONObject(j);
                    MonthlyReport m = new MonthlyReport();
                    m.setAvg(sbObj.getString("Avg"));
                    m.setPay(sbObj.getString("Pay"));
                    m.setName(sbObj.getString("Name"));
                    m.setTotal(sbObj.getString("Total"));
                    ls.add(m);
                }
                listDataChild.put(listDataHeader.get(i), ls);
            }



            listAdapter = new ExpandableListAdapter(v.getContext(), listDataHeader, listDataChild);

            // setting list adapter
            expListView.setAdapter(listAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onFailure(int statusCode, Header[] headers,
                          Throwable throwable, JSONObject errorResponse) {
        if (throwable instanceof java.net.SocketTimeoutException) {
            Toast.makeText(v.getContext(), "连接超时", Toast.LENGTH_SHORT).show();
            return;
        }
        Log.i(LOG_TAG, "提交失败", throwable);
        Toast.makeText(v.getContext(), "获取数据失败", Toast.LENGTH_SHORT).show();
    }
}