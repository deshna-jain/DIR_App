package com.example.deshnajain.drsystemapp;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.deshnajain.drsystemapp.Adp.RecyclerViewAdp;
import com.example.deshnajain.drsystemapp.Database.DatabaseHelper;
import com.example.deshnajain.drsystemapp.Database.NotificationTable;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
        public SearchView msearchView;
        public RecyclerView mrecyclerView;
        public FloatingActionButton mfloatingBtn;
        DatabaseHelper dbHelper;
        Cursor cursor;

        String title;
        String domain;
        String des;
        String summary;
        String srt_date;
        String end_date;
        String branch;
        String sem;
        String id;
        ArrayList<NotificationTable> notificationTables= new ArrayList<>();
        private Object NotificationTable;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search);

            msearchView = (SearchView) findViewById(R.id.searchView);
            mrecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            mfloatingBtn = (FloatingActionButton) findViewById(R.id.floatingActionButton);
            dbHelper=  DatabaseHelper.getInstance(this);
            cursor=dbHelper.getDataFromNotification();
            msearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    notificationTables.clear();
                    //TODO setup adapter for recycler
                    try {
                        if (cursor != null) {
                            if (cursor.getCount() > 0) {
                                cursor.moveToFirst();
                                do {
                                    NotificationTable keys = new NotificationTable(id, title, domain, srt_date, end_date, des, summary, branch, sem);
                                    String id = cursor.getString(cursor.getColumnIndex(keys.getId()));
                                    String title = cursor.getString(cursor.getColumnIndex(keys.getTitle()));
                                    String des = cursor.getString(cursor.getColumnIndex(keys.getDes()));
                                    String domain = cursor.getString(cursor.getColumnIndex(keys.getDomain()));
                                    String branch = cursor.getString(cursor.getColumnIndex(keys.getBranch()));
                                    String summary = cursor.getString(cursor.getColumnIndex(keys.getSummary()));
                                    String srt_date = cursor.getString(cursor.getColumnIndex(keys.getSrt_date()));
                                    String end_date = cursor.getString(cursor.getColumnIndex(keys.getEnd_date()));
                                    String sem = cursor.getString(cursor.getColumnIndex(keys.getSem()));
                                    if (title.contains(s)) {
                                        notificationTables.add(
                                                new NotificationTable(id, title, domain, srt_date, end_date, des, summary, branch, sem)
                                        );
                                    } else if (des.contains(s)) {
                                        notificationTables.add(
                                                new NotificationTable(id, title, domain, srt_date, end_date, des, summary, branch, sem)
                                        );

                                    }
                                } while (cursor.moveToNext());
                            }
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    return false;
                }


                @Override
                public boolean onQueryTextChange(String s) {
                    return false;
                }
            });

            mrecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayout.VERTICAL,false));
            mrecyclerView.addItemDecoration(
                    new DividerItemDecoration(this,
                            LinearLayout.VERTICAL));
            RecyclerViewAdp recyclerViewAdp = new RecyclerViewAdp(this, notificationTables);
            mrecyclerView.setAdapter(recyclerViewAdp);


        }


    }

