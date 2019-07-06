package com.example.deshnajain.drsystemapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deshnajain.drsystemapp.Database.DatabaseHelper;
import com.example.deshnajain.drsystemapp.Database.NotificationTable;

import java.util.Calendar;

import static android.widget.Toast.LENGTH_LONG;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btn;
    private EditText title, domain, branch, sem, des, summary,srt,end;
    private Calendar calendar;
    private int syear, smonth, sday, eyear, emonth, eday;
    private DatabaseHelper databaseHelper;

    //String[] strings = {"CS", "IT", "EC", "EI", "ME", "CE", "IP", "BM"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        title = (EditText) findViewById(R.id.title);
        domain = (EditText) findViewById(R.id.domain);
        branch = (EditText) findViewById(R.id.brnch);
        sem = (EditText) findViewById(R.id.sem);
        des = (EditText) findViewById(R.id.des);
        srt=findViewById(R.id.srt_date);
        end=findViewById(R.id.end);
        summary = (EditText) findViewById(R.id.summary);



        btn = findViewById(R.id.sendbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notification();

            }
        });
        calendar = Calendar.getInstance();
        eyear = calendar.get(Calendar.YEAR);

        emonth = calendar.get(Calendar.MONTH);
        eday = calendar.get(Calendar.DAY_OF_MONTH);
        Calendar calendar1 = Calendar.getInstance();
        syear = calendar.get(Calendar.YEAR);

        smonth = calendar.get(Calendar.MONTH);
        sday = calendar.get(Calendar.DAY_OF_MONTH);

    }
    public void setDate(View view) {
        if(view==srt) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    month = month + 1;
                    String date = day + "/" + month + "/" + year;
                    srt.setText(date);
                }
            }, syear, smonth, sday);
            datePickerDialog.show();
        }
        else if(view==end){
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    month = month + 1;
                    String date = day + "/" + month + "/" + year;
                    end.setText(date);
                }
            }, eyear, emonth, eday);
            datePickerDialog.show();
        }
        }




    private void notification(){
        if (title.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please provide Title.", Toast.LENGTH_LONG).show();
        } else if (domain.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please provide domain", Toast.LENGTH_LONG).show();
        } else if (branch.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please provide branch.", Toast.LENGTH_LONG).show();
        } else if (sem.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please provide sem.", Toast.LENGTH_LONG).show();
        } else if (des.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please provide description.", Toast.LENGTH_LONG).show();
        } else if (srt.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please provide start date.", Toast.LENGTH_LONG).show();
        } else if (summary.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please provide summary.", Toast.LENGTH_LONG).show();
        } else {
            databaseHelper = DatabaseHelper.getInstance(this);
            SharedPreferences sharedPreferences= getSharedPreferences("DrsystemApp",Context.MODE_PRIVATE);
            String id = sharedPreferences.getString("SkeyId","" );

            NotificationTable notify = new NotificationTable(id, title.getText().toString(), domain.getText().toString(), srt.getText().toString(), end.getText().toString(), des.getText().toString(), summary.getText().toString(), branch.getText().toString(), sem.getText().toString());
            if (databaseHelper.addNotificationData(notify)) {
                Toast.makeText(this, "Notification Sent", Toast.LENGTH_LONG).show();
                this.finish();
                noti_count();
            } else {
                Toast.makeText(this, "Notification Not Sent", Toast.LENGTH_LONG).show();
            }

        }
    }

        private void noti_count(){
            try{
        databaseHelper = DatabaseHelper.getInstance(this);
        Cursor cursor = databaseHelper.getDataFromNotification();
        Toast.makeText(this, "Notifications: " + cursor.getCount(), Toast.LENGTH_LONG).show();
    }catch (Exception e){
                e.printStackTrace();
            }
    }

    @Override
    public void onClick(View v) {

    }
}



