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
 * Created by tanapons on 5/4/2559.
 */
public class CustomListOutcome extends ArrayAdapter<String> {
    private String[] date;
    private String[] outcome;
    private String[] monoutcome;
    private String[] note;
    public Activity context;

    public CustomListOutcome(Context context, String[] date, String[] outcome, String[] monoutcome, String[] note) {
        super(context, R.layout.listview_two, date);
        this.context = (Activity) context;
        this.date = date;
        this.outcome = outcome;
        this.monoutcome = monoutcome;
        this.note = note;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.listview_two, null, true);
        TextView textViewdate = (TextView) listViewItem.findViewById(R.id.textView);
        TextView textViewoutcome = (TextView) listViewItem.findViewById(R.id.TextView2);
        TextView textViewmonoutcome = (TextView) listViewItem.findViewById(R.id.textmonoutcome);
        TextView textViewnote = (TextView)listViewItem.findViewById(R.id.textnote);

        textViewdate.setText(date[position]);
        textViewoutcome.setText(outcome[position]);
        textViewmonoutcome.setText(monoutcome[position]);
        textViewnote.setText(note[position]);

        return listViewItem;
    }



}
