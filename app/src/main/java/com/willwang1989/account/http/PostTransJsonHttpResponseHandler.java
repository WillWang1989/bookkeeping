package com.willwang1989.account.http;


import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.willwang1989.asbookkeeping.R;
import com.willwang1989.account.bean.AccountInfo;

public class PostTransJsonHttpResponseHandler extends JsonHttpResponseHandler {

    private View v = null;
    public PostTransJsonHttpResponseHandler(View v, AccountInfo data) {
        this.v = v;

    }

    public void onSuccess(int statusCode, Header[] headers, JSONObject res) {
        try {

            String msg = res.getString("msg");
            Toast.makeText(v.getContext(), msg, Toast.LENGTH_SHORT)
                    .show();
            EmptyView(v);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void EmptyView(View ma) {
        EditText txtItem = (EditText) ma.findViewById(R.id.txtItem);
        EditText txtAccount = (EditText) ma.findViewById(R.id.txtAccount);
        EditText txtRemarks = (EditText) ma.findViewById(R.id.txtRemarks);
        RadioButton rdbtnPersonal = (RadioButton) ma
                .findViewById(R.id.rdbtnPersonal);
        RadioButton rdbtnCommunity = (RadioButton) ma
                .findViewById(R.id.rdbtnCommunity);
        txtItem.setText("");
        txtAccount.setText("");
        txtRemarks.setText("");
        rdbtnPersonal.setChecked(false);
        rdbtnCommunity.setChecked(false);

    }

    public void onFailure(int statusCode, Header[] headers,
                          Throwable throwable, JSONObject errorResponse) {

        //TODO：未联网，保存在本地
//		Long l = DatabaseHelper.Init(ac.getApplicationContext()).Insert(data);
//		Log.i("TEST INSERT", "INSERT :" + l);

        EmptyView(v);
        Toast.makeText(v.getContext(), "数据提交失败",
                Toast.LENGTH_SHORT).show();
//        ac.startActivity(new Intent().setClass(ac.getApplicationContext(),
//                DetailActivity.class));

    }
}
