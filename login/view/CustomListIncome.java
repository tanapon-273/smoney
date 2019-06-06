package com.example.kittisaks.login.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kittisaks.login.R;

/**
 * Created by tanapons on 4/4/2559.
 */
public class CustomListIncome extends ArrayAdapter<String> {
    private String[] id;
    private String[] date;
    private String[] income;
    private String[] monincome;
    private String[] note;
    public Activity context;

    public CustomListIncome(Context context, String[] id, String[] date, String[] income, String[] monincome, String[] note) {
        super(context, R.layout.listview_one, date);
        this.context = (Activity) context;
        this.id = id;
        this.date = date;
        this.income = income;
        this.monincome = monincome;
        this.note = note;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.listview_one, null, true);
        TextView textViewdate = (TextView) listViewItem.findViewById(R.id.textView);
        TextView textViewincome = (TextView) listViewItem.findViewById(R.id.TextView2);
        TextView textViewmonincome = (TextView) listViewItem.findViewById(R.id.textmonincome);
        TextView textViewnote = (TextView)listViewItem.findViewById(R.id.textnote);

        textViewdate.setText(date[position]);
        textViewincome.setText(income[position]);
        textViewmonincome.setText(monincome[position]);
        textViewnote.setText(note[position]);

        return listViewItem;
    }



}
