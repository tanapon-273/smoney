package com.example.kittisaks.login.activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kittisaks.login.R;
import com.example.kittisaks.login.manager.http.ParseJsonIncome;
import com.example.kittisaks.login.manager.http.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * Created by tanapons on 8/4/2559.
 */
public class UpdateIncome extends AppCompatActivity implements View.OnClickListener {

    EditText date1;
    Spinner income1;
    EditText monincome1;
    EditText note1;
    private EditText editT1;
    private Button btn1, buttonUpdate,buttonDelete,buttonCancel;

    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;
    private int mSe;
    private long selectDate;
    static final int DATE_DIALOG_ID = 0;

    private Spinner spnFood;
    private String[] strFood;
    private int id;
    final String user = "abc";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateincome);


        date1 = (EditText) findViewById(R.id.editText1);
        income1 = (Spinner) findViewById(R.id.spnChoose);
        monincome1 = (EditText) findViewById(R.id.edittext1);
        note1 = (EditText) findViewById(R.id.edittext2);
        buttonUpdate = (Button) findViewById(R.id.button2);
        buttonDelete = (Button)findViewById(R.id.buttondl);
        buttonCancel = (Button)findViewById(R.id.button3);

        final Intent i = getIntent();
        id = i.getIntExtra("ttt", 0);

        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateIncome.this,Main2Index.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("username",user);
                startActivity(intent);
            }
        });


        setre();
        strFood = getResources().getStringArray(R.array.setre);
        ArrayAdapter<String> objAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strFood);
        spnFood.setAdapter(objAdapter);

        editT1 = (EditText) findViewById(R.id.editText1);
        btn1 = (Button) findViewById(R.id.button1);
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

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
        getEmployee();
    }


    private void setre() {
        spnFood = (Spinner) findViewById(R.id.spnChoose);
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


    private void getEmployee() {
        class GetEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;



            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UpdateIncome.this, "Fetching...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showEmployee(s);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(ParseJsonIncome.URL_GET_Income, id+"");
                return s;
            }
        }
        GetEmployee ge = new GetEmployee();
        ge.execute();
    }

    private void showEmployee(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(ParseJsonIncome.TAG_JSON_ARRAY_Income);
//            JSONObject c = result.getJSONObject(0);
            String date = ParseJsonIncome.date[id];
            String income = ParseJsonIncome.income[id];
            String monincome = ParseJsonIncome.monincome[id];
            String note = ParseJsonIncome.note[id];

            date1.setText(date);
            income1.setSelected(Boolean.parseBoolean(income));
            monincome1.setText(monincome);
            note1.setText(note);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void updateEmployee() {
        final String date = String.valueOf(selectDate);
        final String income = income1.getSelectedItem().toString().trim();
        final String monincome = monincome1.getText().toString().trim();
        final String note = note1.getText().toString().trim();
       // final String user = "abc";
        class UpdateEmployee extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;
            AlertDialog.Builder builder;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                builder = new AlertDialog.Builder(UpdateIncome.this);
                builder.setTitle("Update");
                builder.setMessage("Update Successfully");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
              //  Toast.makeText(UpdateIncome.this, "Update Successfully", Toast.LENGTH_LONG).show();
              //  loading.dismiss();
//                Intent intent = new Intent(UpdateIncome.this,Main2Index.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("username",user);
//                startActivity(intent);
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("id", ParseJsonIncome.id[id]+"");
                hashMap.put("Date", date);
                hashMap.put("income", income);
                hashMap.put("monincome", monincome);
                hashMap.put("Note", note);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(ParseJsonIncome.URL_UPDATE_Income, hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }


    private void deleteEmployee(){
        class DeleteEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UpdateIncome.this, "Updating...", "Wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(UpdateIncome.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(ParseJsonIncome.URL_DELETE_Income, ParseJsonIncome.id[id]+"");
                return s;
            }
        }

        DeleteEmployee de = new DeleteEmployee();
        de.execute();
    }

    private void confirmDeleteEmployee(){
        android.support.v7.app.AlertDialog.Builder alertDialogBuilder = new android.support.v7.app.AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to delete this Income?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        deleteEmployee();
                        finish();
//                        Intent intent = new Intent(UpdateIncome.this,Main2Index.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);

                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        android.support.v7.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonUpdate){
            updateEmployee();
        }

        if(v == buttonDelete){
            confirmDeleteEmployee();
        }
    }
}

