package com.example.kittisaks.login.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.kittisaks.login.manager.http.ParseJsonIncome;
import com.example.kittisaks.login.manager.http.ParseJsonOutcome;
import com.example.kittisaks.login.R;
import com.example.kittisaks.login.manager.http.RequestHandler;
import com.example.kittisaks.login.activity.UpdateOutcome;
import com.example.kittisaks.login.view.CustomListOutcome;


public class ONE_Expenditure extends Fragment {
    public static final String JSON_URL = "http://wazos.esy.es/hostdroid/select_user_outcome.php?username=";
    private ListView listView;

    SwipeRefreshLayout mSwipeRefreshLayout;
    public ONE_Expenditure() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one_expenditure, container, false);
        listView = (ListView) view.findViewById(R.id.listView23);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.activity_main_swipe_refresh_layout2);
//        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4183D7"),
//                Color.parseColor("#F62459"),
//                Color.parseColor("#62ff00"),
//                Color.parseColor("#F4D03F"));
        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#FFFFFF"));
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.CDM);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        getUserOutcome();
                    }

                }, 1500);
            }
        });
        getUserOutcome();
        return view;
    }

    private void getUserOutcome() {
        SharedPreferences settings1 = this.getActivity().getSharedPreferences("loginUser", 0);
        final String strUsername = settings1.getString("username", "");
        class GetUserOutcome extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                showJSON(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(JSON_URL,strUsername);
                return s;
            }
        }
        GetUserOutcome ge = new GetUserOutcome();
        ge.execute();
    }
    private void showJSON(String json) {
        ParseJsonOutcome pj = new ParseJsonOutcome(json);
        pj.ParseJsonOutcome();
        if(ParseJsonIncome.date != null
                && ParseJsonIncome.income != null
                && ParseJsonIncome.monincome != null
                && ParseJsonIncome.note != null ) {
            CustomListOutcome cl = new CustomListOutcome(getContext(),
                    ParseJsonOutcome.date,
                    ParseJsonOutcome.outcome,
                    ParseJsonOutcome.monoutcome,
                    ParseJsonOutcome.note);
                listView.setAdapter(cl);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> myAdapter, View myView, int position, long mylng) {
                        Intent intent = new Intent(getContext(), UpdateOutcome.class);
                        intent.putExtra("ttt", position);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }

                });
        }
    }
}
