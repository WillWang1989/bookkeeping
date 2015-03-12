package com.willwang1989.asbookkeeping;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.willwang1989.account.bean.AccountInfo;
import com.willwang1989.account.http.HttpHelper;
import com.willwang1989.account.http.PostTransJsonHttpResponseHandler;
import com.willwang1989.account.utils.commons;



public class NewItemFragment extends Fragment {
    private static final String PostUri = "/posttrans/";
    private Button btnSubmit = null;
    public NewItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.new_item, null);
        btnSubmit = (Button)v.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText txtItem = (EditText)v.findViewById(R.id.txtItem);
                EditText txtAccount = (EditText)v.findViewById(R.id.txtAccount);
                EditText txtRemarks = (EditText)v.findViewById(R.id.txtRemarks);
                RadioButton rdbtnPersonal = (RadioButton)v.findViewById(R.id.rdbtnPersonal);
                RadioButton rdbtnCommunity = (RadioButton)v.findViewById(R.id.rdbtnCommunity);

                if ("".equals(txtItem.getText().toString())
                        || "".equals(txtAccount.getText().toString())) {
                    Toast.makeText(v.getContext(), "请输入项目和金额",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!commons.isDouble(txtAccount.getText().toString())) {
                    Toast.makeText(v.getContext(), "请输入数字",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!rdbtnCommunity.isChecked() && !rdbtnPersonal.isChecked()) {
                    Toast.makeText(v.getContext(), "请选择消费类型",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                long createTime = System.currentTimeMillis() / 1000L;
                double account = Double.parseDouble(txtAccount.getText()
                        .toString());

                SharedPreferences sp =v.getContext().getSharedPreferences("u", Context. MODE_PRIVATE);
                String uid = sp.getString("uid", "");
                AccountInfo data = new AccountInfo();
                data.setRemarks(txtRemarks.getText().toString());
                data.setAmount(account);
                data.setCreateTime(createTime);
                data.setItem(txtItem.getText().toString());
                data.setIsPersonal(rdbtnPersonal.isChecked() ? 1 : 0);
                data.setUid(Integer.parseInt(uid));
                RequestParams params = InitReqParams(data);
                HttpHelper.post(PostUri, params,
                        new PostTransJsonHttpResponseHandler(v,
                                data));
            }

            private RequestParams InitReqParams(AccountInfo data) {
                RequestParams params = new RequestParams();
                params.put("Amount", data.getAmount());
                params.put("Item", data.getItem());
                params.put("Remarks", data.getRemarks());
                params.put("CreateTime", data.getCreateTime());
                params.put("IsPersonal", data.getIsPersonal());
                params.put("Uid", data.getUid());
                return params;
            }
        });
        return v;
    }

}
