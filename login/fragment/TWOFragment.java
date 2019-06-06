package com.example.kittisaks.login.fragment;

import android.content.Context;
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
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.kittisaks.login.R;
import com.example.kittisaks.login.manager.http.ParseJsonIncome;
import com.example.kittisaks.login.manager.http.ParseJsonOutcome;
import com.example.kittisaks.login.manager.http.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class TWOFragment extends Fragment {
    Locale locale = Locale.getDefault();
    double total = 0.0;
    ArrayList<String> arrayTotal_income = new ArrayList<>();
    ArrayList<String> arrayTotal_outcome = new ArrayList<>();
    SwipeRefreshLayout mSwipeRefreshLayout;

    private ListView lisView1;
    private TextView mDY, mY;
    private ArrayList<String> itemAdapter = new ArrayList<>();

    public TWOFragment() {
        // Required empty public constructor
    }

    public static long convertStringToTimestamp(String str_date) {
        try {
            DateFormat formatter;
            formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            // you can change format of date
            Date date = formatter.parse(str_date);
            java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());

            return timeStampDate.getTime();
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
            return 0;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        lisView1 = (ListView) view.findViewById(R.id.listView);
        lisView1.setAdapter(new EfficientAdapter(getActivity()));
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.activity_main_swipe_refresh_layout);
    //    mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#4183D7"), Color.parseColor("#F62459"), Color.parseColor("#62ff00"), Color.parseColor("#F4D03F"));
        mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor("#FFFFFF"));
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.TTM);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        getUserIncome();
                    }

                }, 1500);
            }
        });

        getUserIncome();

        return view;
    }

    //จัดเรียงวันที่
    public ArrayList sortHashMapByValues(Map passedMap) {
        List mapKeys = new ArrayList(passedMap.keySet());
        List mapValues = new ArrayList(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        ArrayList<String> sortMonth = new ArrayList<>();

        Iterator valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Object val = valueIt.next();
            Iterator keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                Object key = keyIt.next();
                String comp1 = passedMap.get(key).toString();
                String comp2 = val.toString();


                if (comp1.equals(comp2)) {
                    passedMap.remove(key);
                    mapKeys.remove(key);
                    sortMonth.add(String.valueOf(key));
                    break;
                }
            }

        }

        return sortMonth;
    }

    String JsonIncome, JsonOutcome;
    private void getUserIncome() {
        SharedPreferences settings1 = this.getActivity().getSharedPreferences("loginUser", 0);
        final String strUsername = settings1.getString("username", "");
        class GetUserIncome extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if(s.equalsIgnoreCase("success"))
                    showDate(JsonIncome,JsonOutcome);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                JsonIncome = rh.sendGetRequestParam(ParseJsonIncome.GET_DATE_Income, strUsername);

                RequestHandler rs = new RequestHandler();
                JsonOutcome = rs.sendGetRequestParam(ParseJsonOutcome.GET_DATE_Outcome, strUsername);
                return "success";
            }
        }
        GetUserIncome ge = new GetUserIncome();
        ge.execute();
    }

    private void showDate(String jsonIncome, String jsonOutcome) {

        arrayTotal_income = new ArrayList<>();
        arrayTotal_outcome = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        int cYear = calendar.get(Calendar.YEAR);

        try {
            JSONObject jsonObject_income = new JSONObject(jsonIncome);
            JSONArray result_income = jsonObject_income.getJSONArray(ParseJsonIncome.JSON_ARRAY);

            JSONObject jsonObject_outcome = new JSONObject(jsonOutcome);
            JSONArray result_outcome = jsonObject_outcome.getJSONArray(ParseJsonOutcome.JSON_ARRAY);

            for (int i = 0; i < 12; i++) {

                calendar.set(cYear, i, 1);
                long dateFrom = calendar.getTimeInMillis() / 1000;

                calendar.set(cYear, i + 1, 1);
                long dateTo = calendar.getTimeInMillis() / 1000;

                /// In come ///
                total = 0.0;

                for (int k = 0; k < result_income.length(); k++) {
                    long thisDate = convertStringToTimestamp(((JSONObject) result_income.get(k)).get(ParseJsonIncome.Date).toString()) / 1000;
                    if (dateFrom <= thisDate && thisDate <= dateTo) {
                        total += Double.parseDouble(((JSONObject) result_income.get(k)).getString(ParseJsonIncome.Monincome));
                    }
                }
                arrayTotal_income.add(total + "");
                /// END In come ///

                /// Out come ///
                total = 0.0;
                for (int k = 0; k < result_outcome.length(); k++) {
                    long thisDate = convertStringToTimestamp(((JSONObject) result_outcome.get(k)).get(ParseJsonOutcome.Date).toString()) / 1000;
                    if (dateFrom <= thisDate && thisDate <= dateTo) {
                        total += Double.parseDouble(((JSONObject) result_outcome.get(k)).getString(ParseJsonOutcome.Monoutcome));
                    }
                }
                arrayTotal_outcome.add(total + "");
                /// END Out come ///
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        showList();
    }

    private void showList() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Calendar mCal = Calendar.getInstance();
                Map<String, Integer> mMonth = mCal.getDisplayNames(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
                ArrayList<String> mSortMonth = sortHashMapByValues(mMonth);
                itemAdapter = new ArrayList<>();
                for (int i = 0; i < mSortMonth.size(); i++) {
                    itemAdapter.add(mSortMonth.get(i));
                }
                EfficientAdapter adp = (EfficientAdapter) lisView1.getAdapter();
                adp.notifyDataSetChanged();
                lisView1.setAdapter(adp);
            }
        });
    }

    public class EfficientAdapter extends BaseAdapter {

        public Context mContext;
        public LayoutInflater mInflater;


        public EfficientAdapter(Context context) {
            mContext = context;
            mInflater = LayoutInflater.from(mContext);

        }

        @Override
        public int getCount() {
            return itemAdapter.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup viewGroup) {
            ViewHolder holdler = null;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.fragment_fragment_income, null);
                holdler = new ViewHolder();
                holdler.mTextView = (TextView) convertView.findViewById(R.id.TextView1);
                holdler.mTextView_income = (TextView) convertView.findViewById(R.id.TextView2);
                holdler.mTextView_outcome = (TextView) convertView.findViewById(R.id.TextView3);
                convertView.setTag(holdler);

            } else {
                holdler = (ViewHolder) convertView.getTag();
            }

            if(itemAdapter.get(i) != null)
                holdler.mTextView.setText(itemAdapter.get(i));
            if(arrayTotal_income.size() == itemAdapter.size())
                holdler.mTextView_income.setText(arrayTotal_income.get(i));
            if(arrayTotal_outcome.size() == itemAdapter.size())
                holdler.mTextView_outcome.setText(arrayTotal_outcome.get(i));
            return convertView;
        }
    }

    public class ViewHolder {
        public TextView mTextView, mTextView_income, mTextView_outcome;
    }
}


