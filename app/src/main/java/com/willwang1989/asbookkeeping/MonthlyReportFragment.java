package com.willwang1989.asbookkeeping;

import java.util.HashMap;
import java.util.List;

import com.willwang1989.account.bean.MonthlyReport;
import com.willwang1989.account.http.HttpHelper;
import com.willwang1989.account.http.MonthlyReportJsonHttpResponseHandler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class MonthlyReportFragment extends Fragment {
	private final String REPORT_URL = "/getmonthlyreport/";
	ExpandableListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<MonthlyReport>> listDataChild;

	// http://www.androidhive.info/2013/07/android-expandable-list-view-tutorial/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_monthlyreport, null);

		HttpHelper.get(REPORT_URL, null,
				new MonthlyReportJsonHttpResponseHandler(v));

		return v;
	}

}
