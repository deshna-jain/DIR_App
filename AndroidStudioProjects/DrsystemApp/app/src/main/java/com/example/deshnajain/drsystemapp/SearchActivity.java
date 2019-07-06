package com.example.deshnajain.drsystemapp;

import android.Manifest;
import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.deshnajain.drsystemapp.Adp.RecyclerViewAdp;
import com.example.deshnajain.drsystemapp.Database.DatabaseHelper;
import com.example.deshnajain.drsystemapp.Database.NotificationTable;
import com.example.deshnajain.drsystemapp.Pdf.MyPdf;

import java.io.File;
import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.M;

public class SearchActivity extends AppCompatActivity {
        private SearchView msearchView;
        private RecyclerView mrecyclerView;
        private FloatingActionButton floatingActionButton;
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
    private int PERMISSIONS_MULTIPLE_REQUEST = 100;
    String[] stringsPermission = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    //private Object NotificationTable;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_search);

            msearchView = (SearchView) findViewById(R.id.searchView);
            mrecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
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
                                    NotificationTable keys = new NotificationTable();
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
                                mrecyclerView.setAdapter(new RecyclerViewAdp(SearchActivity.this, notificationTables));
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
            mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                        if(checkPermission()) {
                            callPdf();
                        }
                }
                }
            });
        }
@RequiresApi(api = Build.VERSION_CODES.M)
    private boolean checkPermission() {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) +
            ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
        ) ||
                ActivityCompat.shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            Snackbar.make(
                    this.findViewById(android.R.id.content),
                    "Please Grant Permissions to  Application for Storage",
                    Snackbar.LENGTH_INDEFINITE
            ).setAction("ENABLE", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            requestPermissions(
                                    stringsPermission
                                    ,
                                    PERMISSIONS_MULTIPLE_REQUEST
                            );
                        }
                    }
            ).show();
        } else {
            requestPermissions(
                    stringsPermission
                    ,
                    PERMISSIONS_MULTIPLE_REQUEST
            );
        }
    } else {
        return true;
    }
    return false;
}
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100) {

            boolean readExternalFile = grantResults[0] == PackageManager.PERMISSION_GRANTED;
            boolean writeExternalFile = grantResults[1] == PackageManager.PERMISSION_GRANTED;

            if (readExternalFile && writeExternalFile) {
               /* if (isFromCamera) {
                    cameraImageCall(activity!!)
                } else if (isFromGallery) {
                    galleryImageCall(activity!!)
                }*/
            } else Snackbar.make(
                    getWindow().getDecorView(),
                    "Please Grant Permissions to work with camera and gallery.",
                    Snackbar.LENGTH_INDEFINITE
            ).setAction("ENABLE",
                    new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.M)
                        @Override
                        public void onClick(View view) {
                            requestPermissions(
                                    stringsPermission
                                    ,
                                    PERMISSIONS_MULTIPLE_REQUEST
                            );
                        }

                    }).show();
        }


    }

    private void callPdf(){
        String fileName=System.currentTimeMillis()+"sgsits.pdf";
        new MyPdf().write(this,fileName,notificationTables);

        Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);

        try {
            if (Build.VERSION.SDK_INT > M) {
                try {
                    Uri photoURI = FileProvider.getUriForFile(
                            this,
                            getApplicationContext().getPackageName() +".my.package.name.provider",
                            new File( "/sdcard/Download/"+fileName)
                    );
                    pdfOpenintent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    pdfOpenintent.setDataAndType(
                            photoURI,
                            "application/pdf"
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else{
                pdfOpenintent.setDataAndType(
                        Uri.parse("file://" + "/sdcard/Download"+"/" + fileName),
                        "application/pdf"
                );

            }
            startActivity(pdfOpenintent);
        } catch (ActivityNotFoundException e) {

        }
    }


    }

