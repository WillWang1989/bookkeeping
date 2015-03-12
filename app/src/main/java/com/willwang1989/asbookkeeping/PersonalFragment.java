package com.willwang1989.asbookkeeping;

import com.loopj.android.http.RequestParams;
import com.willwang1989.account.http.HttpHelper;
import com.willwang1989.account.http.TransJsonHttpResposeHandler;
import com.willwang1989.account.utils.commons;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PersonalFragment extends Fragment {
	private final String TRANS_URL = "/getlisttrans/";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_community, null);
		RequestParams params = new RequestParams();
		Long t = commons.GetCurrentMonthTimeStamp();
		params.add("t", "" + t);
		params.add("type", "1");
		SharedPreferences sp = this.getActivity().getSharedPreferences("u",
				android.content.Context.MODE_PRIVATE);
		String uid = sp.getString("uid", "0");
		params.add("uid", uid);
		HttpHelper.get(TRANS_URL, params, new TransJsonHttpResposeHandler(v));
		return v;

	}
}
