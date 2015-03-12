package com.willwang1989.asbookkeeping;

/**
 * Created by hp on 2015/1/11.
 */

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.willwang1989.account.http.HttpHelper;
import com.willwang1989.account.http.LoginJsonHttpResposeHandler;
import com.willwang1989.account.utils.commons;

public class LoginActivity extends Activity {
    private static final String LOG_TAG = "LoginActivity";
    private static final String LOGIN_URI = "/login/";
    EditText txtUserName;
    EditText txtPassword;
    Button btnLogin;
    Button btnCancel;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        AlreadyLogin();

        setContentView(R.layout.activity_login);
        txtUserName = (EditText) this.findViewById(R.id.txtUname);
        txtPassword = (EditText) this.findViewById(R.id.txtPwd);
        btnLogin = (Button) this.findViewById(R.id.btnLogin);
        btnCancel = (Button) this.findViewById(R.id.btnCancel);
        btnLogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                String un = txtUserName.getText().toString();
                String p = txtPassword.getText().toString();

                if ("".equals(un) || "".equals(p)) {
                    Toast.makeText(LoginActivity.this, "请输入用户名和密码",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                RequestParams params = new RequestParams();
                params.put("UserName", un);
                p = commons.SHA256(p);
                Log.i(LOG_TAG, "SHA256:" + p);
                System.out.println("SHA256:" + p);
                params.put("Password", p);

                Log.i(LOG_TAG, "开始提交");
                HttpHelper.post(LOGIN_URI, params,
                        new LoginJsonHttpResposeHandler(LoginActivity.this,
                                txtUserName.getText().toString(), txtPassword
                                .getText().toString()));
                Log.i(LOG_TAG, "结束提交");

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String loginUrl = "http://window8linux.sinaapp.com/signup/";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(loginUrl));
                startActivity(browserIntent);
            }
        });
    }

    private void AlreadyLogin() {
        SharedPreferences sp2 = getSharedPreferences("u", MODE_PRIVATE);
        String un = sp2.getString("un", "");
        String p = sp2.getString("p", "");
        if (!"".equals(p) && !"".equals(un)) {
            long t = sp2.getLong("time", 0L);
            if (t != 0) {
                if (System.currentTimeMillis() / 1000L - t < 259200L) {// 保存30天，30后过期重新登陆
                    StartMainActivity();
                }

            }
        }
    }

    private void StartMainActivity() {
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
