package com.willwang1989.asbookkeeping;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.loopj.android.http.RequestParams;
import com.willwang1989.account.utils.commons;
import com.willwang1989.account.http.HttpHelper;
import com.willwang1989.account.http.TransJsonHttpResposeHandler;

;

public class CommunityFragment extends Fragment {

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
		params.add("type", "0");
		HttpHelper.get(TRANS_URL, params, new TransJsonHttpResposeHandler(v));
		return v;
	}
}
