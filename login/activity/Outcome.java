package com.example.kittisaks.login.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.kittisaks.login.R;
import com.example.kittisaks.login.manager.http.BackgroundTask;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by tanapons on 21/3/2559.
 */

public class Outcome extends AppCompatActivity {
    EditText date;
    Spinner outcome;
    EditText monoutcome;
    EditText note;
    AlertDialog.Builder builder;

    private EditText editT1;
    private Button btn1;

    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private int mSe;

    static final int DATE_DIALOG_ID = 0;

    private long selectDate;
    private Spinner spnFood;
    private String[] strFood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outcon);

        date = (EditText)findViewById(R.id.editText1);
        outcome = (Spinner) findViewById(R.id.spnChoose);
        monoutcome = (EditText)findViewById(R.id.edittext1);
        note =(EditText)findViewById(R.id.edittext2);
        Button save = (Button) findViewById(R.id.button2);
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (date.getText().toString().equals("") || outcome.getSelectedItem().toString().equals("") || monoutcome.getText().toString().equals("")) {
                    builder = new AlertDialog.Builder(Outcome.this);
                    builder.setTitle("Something went wong");
                    builder.setMessage("กรุณากรอกข้อมูล");
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else {
                    SharedPreferences settings1 = getSharedPreferences("loginUser", 0);
                    String strUsername = settings1.getString("username", "");
                    BackgroundTask backgroundTask = new BackgroundTask(Outcome.this);
                    backgroundTask.execute("outcome",strUsername,String.valueOf(selectDate),outcome.getSelectedItem().toString(),monoutcome.getText().toString(),note.getText().toString());
                }

            }
        });


        Button cancel = (Button) findViewById(R.id.button3);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Open Form Main
                Intent newActivity = new Intent(Outcome.this, Main2Index.class);
                newActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(newActivity);
            }
        });

                setex();
        // editText1
        editT1 = (EditText) findViewById(R.id.editText1);
        btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });


        strFood = getResources().getStringArray(R.array.setex);
        ArrayAdapter<String> objAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strFood);
        spnFood.setAdapter(objAdapter);

        // get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        mSe = c.get(Calendar.SECOND);

        // display the current date
        updateCurrentDate();
    }
    private void setex() {
        spnFood = (Spinner)findViewById(R.id.spnChoose);
    }




    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }

    // updates the date we display in the editText
    private void updateCurrentDate() {
        Calendar currentCal = Calendar.getInstance();
        Calendar cal = new GregorianCalendar(mYear ,
                mMonth ,
                mDay ,
                currentCal.get(Calendar.HOUR_OF_DAY) ,
                currentCal.get(Calendar.MINUTE) ,
                0);
        selectDate = cal.getTimeInMillis();
        editT1.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(mYear).append("-")
                        .append(mMonth + 1).append("-")
                        .append(mDay).append(" ")
                        .append(mHour).append(":")
                        .append(mMinute).append(":")
                        .append(mSe).append(""));
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateCurrentDate();
                }
            };

}