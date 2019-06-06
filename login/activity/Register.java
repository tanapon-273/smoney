package com.example.kittisaks.login.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kittisaks.login.R;
import com.example.kittisaks.login.manager.http.BackgroundTask;

/**
 * Created by tanapons on 30/3/2559.
 */
public class Register extends AppCompatActivity {

    EditText Name,Username,Pass,Confpass;
    Button regisbt;
    AlertDialog.Builder builder;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Name = (EditText)findViewById(R.id.name);
        Username = (EditText)findViewById(R.id.new_user_name);
        Pass = (EditText)findViewById(R.id.new_user_pass);
        Confpass = (EditText)findViewById(R.id.con_user_pass);
        regisbt = (Button)findViewById(R.id.regibt);
        regisbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Name.getText().toString().equals("")||Username.getText().toString().equals("")||Pass.getText().toString().equals(""))
                {
                    builder = new AlertDialog.Builder(Register.this);
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

                else if (!(Pass.getText().toString().equals(Confpass.getText().toString())))
                {

                    builder = new AlertDialog.Builder(Register.this);
                    builder.setTitle("Something went wong");
                    builder.setMessage("You Password are not matching");
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                            Pass.setText("");
                            Confpass.setText("");

                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else {
                    BackgroundTask backgroundTask = new BackgroundTask(Register.this);
                    backgroundTask.execute("register",Name.getText().toString(),Username.getText().toString(),Pass.getText().toString());

                }
            }
        });



    }
}
