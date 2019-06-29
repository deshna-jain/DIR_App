package com.example.deshnajain.drsystemapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.deshnajain.drsystemapp.Database.DatabaseHelper;
import com.example.deshnajain.drsystemapp.Database.NotificationTable;

import static android.widget.Toast.LENGTH_LONG;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btn;
    private EditText title, domain, branch, sem, des, srt, end, summary;
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
        srt = (EditText) findViewById(R.id.srt_date);
        summary = (EditText) findViewById(R.id.summary);
        end = (EditText) findViewById(R.id.end_date);


        btn = findViewById(R.id.sendbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notification();

            }
        });

    }



    private void notification() {
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
            NotificationTable notify = new NotificationTable(title.getText().toString(), domain.getText().toString(), srt.getText().toString(), end.getText().toString(), des.getText().toString(), summary.getText().toString(), branch.getText().toString(), sem.getText().toString());
            if (databaseHelper.addNotificationData(notify)) {
                Toast.makeText(this, "Notification Sent", Toast.LENGTH_LONG).show();
                this.finish();
            } else {
                Toast.makeText(this, "Notification Not Sent", Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    public void onClick(View v) {

    }
}



