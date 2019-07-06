package com.example.deshnajain.drsystemapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deshnajain.drsystemapp.Database.DatabaseHelper;
import com.example.deshnajain.drsystemapp.Database.NotificationTable;

public class NotificationDesc extends AppCompatActivity {
    private TextView branch;
    private TextView sem;
    private TextView srt;
    private TextView end;
    private TextView title;
    private TextView domain;
    private TextView desc;
    private TextView summary;
    DatabaseHelper databaseHelper= DatabaseHelper.getInstance(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_desc);
        branch=findViewById(R.id.branch);
        sem=findViewById(R.id.semester);
        srt=findViewById(R.id.srt_date1);
        end=findViewById(R.id.end_date1);
        title=findViewById(R.id.title);
        domain=findViewById(R.id.domain1);
        desc=findViewById(R.id.desc1);
        summary=findViewById(R.id.summary1);
        String id = getIntent().getStringExtra(UtilsClass.NAME_LOGIN);
        NotificationTable key = new NotificationTable();
        Cursor cursor= databaseHelper.getDataFromNotification();
        cursor.moveToFirst();
        do {
            if (cursor.getString(cursor.getColumnIndex(key.getNot_id())).equals(id)) {
                branch.setText(cursor.getString(cursor.getColumnIndex(key.getBranch())));
                sem.setText(cursor.getString(cursor.getColumnIndex(key.getSem())));
                srt.setText(cursor.getString(cursor.getColumnIndex(key.getSrt_date())));
                end.setText(cursor.getString(cursor.getColumnIndex(key.getEnd_date())));
                domain.setText(cursor.getString(cursor.getColumnIndex(key.getDomain())));
                title.setText(cursor.getString(cursor.getColumnIndex(key.getTitle())));
                desc.setText(cursor.getString(cursor.getColumnIndex(key.getDes())));
                summary.setText(cursor.getString(cursor.getColumnIndex(key.getSummary())));
                break;
            }else {
                Toast.makeText(this,""+id,Toast.LENGTH_LONG);
            }
        }while (cursor.moveToNext());
    }
}
