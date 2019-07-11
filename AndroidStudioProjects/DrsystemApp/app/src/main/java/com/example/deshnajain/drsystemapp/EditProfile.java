package com.example.deshnajain.drsystemapp;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.deshnajain.drsystemapp.Database.AchievementsTable;
import com.example.deshnajain.drsystemapp.Database.DatabaseHelper;
import com.example.deshnajain.drsystemapp.Database.EducationTable;
import com.example.deshnajain.drsystemapp.Database.EmploymentTable;
import com.example.deshnajain.drsystemapp.Database.SkillsTable;
import com.example.deshnajain.drsystemapp.Database.UserTable;
import com.example.deshnajain.drsystemapp.Utils.GallaryPickerUtil;

import java.util.Calendar;

public class EditProfile extends AppCompatActivity {
    private Spinner etype;
    private Spinner etype1;
    private ImageButton imgBtn;
    String[] strings = {"PostGraduation", "Graduation", "School"};
    private int dyear, dmonth, dday, jyear, jmonth, jday;
    private EditText getDateEt;
    private EditText fname;
    private EditText lname;
    private EditText city;
    private EditText contact;
    private EditText email;
    private EditText inst1;
    private EditText inst2;
    private EditText company;
    private EditText post;
    private EditText dur;
    private EditText skill;
    private EditText yr;
    private EditText ach;
    private EditText stype;
    private EditText getjDate;
    private Button save;
    private String pass;
    private String id;
    private ImageView image;
    private String imgDecodableString;
    private int PERMISSIONS_MULTIPLE_REQUEST = 100;
    DatePickerDialog.OnDateSetListener setListener;

    private DatabaseHelper databaseHelper;
    String[] stringsPermission = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        etype = (Spinner) findViewById(R.id.type1);
        etype1 = (Spinner) findViewById(R.id.type2);
        getDateEt = findViewById(R.id.dobrth);
        getjDate = findViewById(R.id.join_date);
        save = (Button) findViewById(R.id.save);
        fname = findViewById(R.id.f_name);
        lname = findViewById(R.id.l_name);
        email = findViewById(R.id.email);
        city = findViewById(R.id.city);
        contact = findViewById(R.id.contact);
        inst1 = findViewById(R.id.inst1);
        inst2 = findViewById(R.id.inst2);
        company = findViewById(R.id.com);
        post = findViewById(R.id.pos);
        dur = findViewById(R.id.dur);
        skill = findViewById(R.id.sinfo);
        yr = findViewById(R.id.year);
        ach = findViewById(R.id.ainfo);
        stype = findViewById(R.id.stype);
        imgBtn=findViewById(R.id.imageButton);
        image = findViewById(R.id.pimage);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }
        });
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1,
                        strings);
        etype.setAdapter(arrayAdapter);
        etype1.setAdapter(arrayAdapter);
        Calendar calendar = Calendar.getInstance();
        dyear = calendar.get(Calendar.YEAR);

        dmonth = calendar.get(Calendar.MONTH);
        dday = calendar.get(Calendar.DAY_OF_MONTH);
        Calendar calendar1 = Calendar.getInstance();
        jyear = calendar1.get(Calendar.YEAR);
        jmonth = calendar1.get(Calendar.MONTH);
        jday = calendar1.get(Calendar.DAY_OF_MONTH);
        getDateEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(R.id.dobrth);
            }
        });
        getjDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(R.id.join_date);
            }
        });
        databaseHelper=DatabaseHelper.getInstance(this);
        Cursor cursor = databaseHelper.getDataFromUser();
        UserTable key = new UserTable();
        cursor.moveToFirst();
        fname.setText(cursor.getString(cursor.getColumnIndex(key.getF_name())));
        lname.setText(cursor.getString(cursor.getColumnIndex(key.getL_name())));
        email.setText(cursor.getString(cursor.getColumnIndex(key.getEmail())));
        city.setText(cursor.getString(cursor.getColumnIndex(key.getCity())));
        contact.setText(cursor.getString(cursor.getColumnIndex(key.getContact())));
        getDateEt.setText(cursor.getString(cursor.getColumnIndex(key.getDob())));

        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if(checkPermission()){
                        galleryImageCall();
                    }
                } else {
                    galleryImageCall();
                }

            }
        });
    }
    private void galleryImageCall() {
        GallaryPickerUtil.launchGallery(EditProfile.this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GallaryPickerUtil.SELECT_FILE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                // Get the cursor
                Cursor cursor2 = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                // Move to first row
                cursor2.moveToFirst();
                //Get the column index of MediaStore.Images.Media.DATA
                int columnIndex = cursor2.getColumnIndex(filePathColumn[0]);
                //Gets the String value in the column
                imgDecodableString = cursor2.getString(columnIndex);
                cursor2.close();
                // Set the Image in ImageView after decoding the String
                image.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
              //  image.setImageURI(data.getData());

            }

        }
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
                        "Please Grant Permissions to  Application for using camera and gallery",
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
            /*if (isFromCamera) {
                cameraImageCall(activity!!)
            } else if (isFromGallery) {
                galleryImageCall(activity!!)
            }*/
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



    public void setDate(int id) {
        if (id == R.id.dobrth) {

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    month = month + 1;
                    String date = day + "/" + month + "/" + year;
                    getDateEt.setText(date);
                }
            }, dyear, dmonth, dday);
            datePickerDialog.show();
        } else if (id == R.id.join_date) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int day) {
                    month = month + 1;
                    String date = day + "/" + month + "/" + year;
                    getjDate.setText(date);
                }
            }, jyear, jmonth, jday);
            datePickerDialog.show();
        }
    }


    private void saveChanges() {
        //ToDO retrieve data from tables
        databaseHelper = DatabaseHelper.getInstance(this);
        SharedPreferences sharedPreferences = getSharedPreferences("DrsystemApp", Context.MODE_PRIVATE);
        pass = sharedPreferences.getString("SKeyPass", "");
        id = sharedPreferences.getString("SKeyId", "");

        UserTable edit = new UserTable(email.getText().toString(), fname.getText().toString(), lname.getText().toString(),"Female", city.getText().toString(), contact.getText().toString(),pass, getDateEt.getText().toString());
        edit.setId(id);
        edit.setImage(imgDecodableString);
        AchievementsTable achievementsTable = new AchievementsTable(id, ach.getText().toString(), yr.getText().toString());
        EducationTable educationTable = new EducationTable(id, inst1.getText().toString(), etype.getSelectedItem().toString());
        EducationTable educationTable1 = new EducationTable(id, inst2.getText().toString(), etype1.getSelectedItem().toString());
        EmploymentTable employmentTable = new EmploymentTable(id, company.getText().toString(), getjDate.getText().toString(), post.getText().toString(), dur.getText().toString());
        SkillsTable skillsTable = new SkillsTable(id,skill.getText().toString(), stype.getText().toString());
        if ((databaseHelper.editUserData(edit))&&(databaseHelper.addAchievement(achievementsTable))&&(databaseHelper.addEducation(educationTable))&&(databaseHelper.addEducation(educationTable1))&&(databaseHelper.addEmployement(employmentTable))&&(databaseHelper.addSkills(skillsTable))){
            Toast.makeText(this, "Edit Successful", Toast.LENGTH_LONG).show();
            this.finish();
        }else {
            Toast.makeText(this, "Not Edited", Toast.LENGTH_LONG).show();
        }
    }
}



