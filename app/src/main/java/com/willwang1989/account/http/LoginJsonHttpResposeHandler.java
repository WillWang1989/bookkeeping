package com.willwang1989.account.http;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.willwang1989.asbookkeeping.MainActivity;
import com.willwang1989.account.utils.commons;

public class LoginJsonHttpResposeHandler extends JsonHttpResponseHandler {
    private final String LOG_TAG = "";
    private Activity v = null;
    private String u = "";
    private String p = "";

    public LoginJsonHttpResposeHandler(Activity v, String u, String p) {
        this.v = v;
        this.u = u;
        this.p = p;
    }

    public void onSuccess(int statusCode, Header[] headers, JSONObject res) {
        SharedPreferences sp = v.getSharedPreferences("u",
                android.content.Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        try {
            Boolean result = res.getBoolean("result");
            if (!result) {
                Toast.makeText(v.getApplication(), res.getString("msg"),
                        Toast.LENGTH_SHORT).show();
                return;
            }
            JSONObject data = res.getJSONObject("data");
            if (data != null) {
                String uid = data.getString("uid");
                String token = data.getString("token");
                editor.putString("uid", uid);
                editor.putString("token", token);
                editor.putLong("time", System.currentTimeMillis() / 1000L);
                editor.putString("un", u);
                editor.putString("p", commons.SHA256(p));
                editor.commit();
            } else {
                Toast.makeText(v.getApplication(), "未获取到数据", Toast.LENGTH_SHORT)
                        .show();
                return;
            }
        } catch (JSONException e) {

            e.printStackTrace();
            Toast.makeText(v.getApplication(), "未获取到数据", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        Intent intent = new Intent();
        intent.setClass(v.getApplicationContext(), MainActivity.class);
        v.startActivity(intent);
        v.finish();
    }

    public void onFailure(int statusCode, Header[] headers,
                          Throwable throwable, JSONObject errorResponse) {
        if (throwable instanceof java.net.SocketTimeoutException) {
            Toast.makeText(v.getApplication(), "连接超时", Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        Log.i(LOG_TAG, "提交失败", throwable);
        Toast.makeText(v.getApplication(), "提交失败", Toast.LENGTH_SHORT).show();
    }
}