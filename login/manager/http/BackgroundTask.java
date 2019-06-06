package com.example.kittisaks.login.manager.http;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.EditText;

import com.example.kittisaks.login.activity.Main2Index;
import com.example.kittisaks.login.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by tanapons on 21/3/2559.
 */
public class BackgroundTask extends AsyncTask<String,Void,String> {
    String register_url = "http://wazos.esy.es/hostdroid/register.php";
    String login_url = "http://wazos.esy.es/hostdroid/login.php";
    String income_url = "http://wazos.esy.es/hostdroid/insert_income.php";
    String outcome_url = "http://wazos.esy.es/hostdroid/insert_outcome.php";
    Context ctx;
    ProgressDialog progressdialog;
    Activity activity;
    AlertDialog.Builder builder;

    public BackgroundTask(Context ctx) {

        this.ctx = ctx;
        activity = (Activity) ctx;
    }

    @Override
    protected void onPreExecute() {
        builder = new AlertDialog.Builder(activity);
        progressdialog = new ProgressDialog(ctx);
        progressdialog.setTitle("Please wait");
        progressdialog.setMessage("Connect Server...");
        progressdialog.setIndeterminate(true);
        progressdialog.setCancelable(false);
        progressdialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String method = params[0];
        if (method.equals("register")) {
            try {
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String name = params[1];
                String username = params[2];
                String password = params[3];
                String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                httpURLConnection.disconnect();
                Thread.sleep(2500);
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (method.equals("login")) {
            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String username, password;
                username = params[1];
                password = params[2];
                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                httpURLConnection.disconnect();
                Thread.sleep(2500);
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (method.equals("income")) {
            try {
                URL url = new URL(income_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

//                String id = params[1];
                String username = params[1];
                String Date = params[2];
                String income = params[3];
                String monincome = params[4];
                String Note = params[5];
                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("Date", "UTF-8") + "=" + URLEncoder.encode(Date, "UTF-8") + "&" +
                        URLEncoder.encode("income", "UTF-8") + "=" + URLEncoder.encode(income, "UTF-8") + "&" +
                        URLEncoder.encode("monincome", "UTF-8") + "=" + URLEncoder.encode(monincome, "UTF-8") + "&" +
                        URLEncoder.encode("Note", "UTF-8") + "=" + URLEncoder.encode(Note, "UTF-8");
//                URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8") + "&" +
//                        URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                httpURLConnection.disconnect();
                Thread.sleep(2500);
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if (method.equals("outcome")) {
            try {
                URL url = new URL(outcome_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
//                String id = params[1];
                String username = params[1];
                String Date = params[2];
                String outcome = params[3];
                String monoutcome = params[4];
                String Note = params[5];
                String data =
                        URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("Date", "UTF-8") + "=" + URLEncoder.encode(Date, "UTF-8") + "&" +
                        URLEncoder.encode("outcome", "UTF-8") + "=" + URLEncoder.encode(outcome, "UTF-8") + "&" +
                        URLEncoder.encode("monoutcome", "UTF-8") + "=" + URLEncoder.encode(monoutcome, "UTF-8") + "&" +
                        URLEncoder.encode("Note", "UTF-8") + "=" + URLEncoder.encode(Note, "UTF-8");
//                URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8") + "&" +
//                        URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line + "\n");
                }
                httpURLConnection.disconnect();
                Thread.sleep(2500);
                return stringBuilder.toString().trim();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String json) {
        try {
            progressdialog.dismiss();
            JSONObject jsonobject = new JSONObject(json);
            JSONArray jsonArray = jsonobject.getJSONArray("server_response");
            JSONObject JO = jsonArray.getJSONObject(0);
            String code = JO.getString("code");
            String message = JO.getString("message");
            String user = JO.getString("user");
            if (code.equals("reg_true")) {
                showDialog("Registration Success...", message, code);
            } else if (code.equals("reg_false")) {
                showDialog("Registration Failed", message, code);
            } else if (code.equals("login_true")) {

//                ArrayList list = new ArrayList();
//                list.add("222");
//                list.add("333");
//                test.getInstance().setAr(list);
//                test.getInstance().setName("1111");
                Intent intent = new Intent(activity, Main2Index.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("username", user);
                intent.putExtra("message", message);
                activity.startActivity(intent);
                activity.finish();


            } else if (code.equals("Login_failed")) {
                showDialog("Login Failed...", message, code);
            } else if (code.equals("income_true")) {
                showDialog("Insert Income Success...", message, code);
            } else if (code.equals("income_false")) {
                showDialog("Some Server error occurred. Try again...", message, code);
            } else if (code.equals("outcome_true")) {
                showDialog("Insert Outcome Success...", message, code);
            } else if (code.equals("outcome_false")) {
                showDialog("Some Server error occurred. Try again...", message, code);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void showDialog(String title, String message, String code) {

        builder.setTitle(title);
        if (code.equals("reg_true") || code.equals("reg_false")) {
            builder.setMessage(message);
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    activity.finish();

                }
            });
        } else if (code.equals("Login_failed")) {
            builder.setMessage(message);
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditText username, password;
                    username = (EditText) activity.findViewById(R.id.username);
                    password = (EditText) activity.findViewById(R.id.password);
                    username.setText("");
                    password.setText("");
                    dialog.dismiss();
                }
            });
        } else if (code.equals("income_true") || code.equals("income_false")) {
            builder.setMessage(message);
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    activity.finish();

                }
            });
        } else if (code.equals("outcome_true") || code.equals("outcome_false")) {
            builder.setMessage(message);
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    activity.finish();

                }
            });
        }
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}