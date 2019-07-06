package com.example.deshnajain.drsystemapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.deshnajain.drsystemapp.Database.DatabaseHelper;
import com.example.deshnajain.drsystemapp.Database.EducationTable;
import com.example.deshnajain.drsystemapp.Database.EmploymentTable;
import com.example.deshnajain.drsystemapp.Database.UserTable;

public class ProfileView extends AppCompatActivity {
DatabaseHelper databaseHelper=DatabaseHelper.getInstance(this);
private TextView name;
    private TextView company;
    private TextView exp;
    private TextView city;
    private TextView email;
    private TextView contact;
    private TextView clg;
    private TextView schl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        name=findViewById(R.id.uname);
        company=findViewById(R.id.company);
        exp=findViewById(R.id.exp);
        city=findViewById(R.id.cityTv);
        email=findViewById(R.id.emailid);
        contact=findViewById(R.id.contct);
        clg=findViewById(R.id.clg);
        schl=findViewById(R.id.schl);
        Cursor cursor=databaseHelper.getDataFromUser();
        Cursor employ=databaseHelper.getDataFromEmp();
        Cursor edu = databaseHelper.getDataFromEdu();
        UserTable key = new UserTable();
        EmploymentTable emp = new EmploymentTable();
        EducationTable educationTable = new EducationTable();
        SharedPreferences sharedPreferences = getSharedPreferences("DrsystemApp", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("SKeyId", "");
        cursor.moveToFirst();
        employ.moveToFirst();
        edu.moveToFirst();
            if(cursor.getString(cursor.getColumnIndex(key.getId())).equals(id)){
                String user= cursor.getString(cursor.getColumnIndex(key.getF_name()))+" "+cursor.getString(cursor.getColumnIndex(key.getL_name()));
                name.setText(user);
                email.setText(cursor.getString(cursor.getColumnIndex(key.getEmail())));
                city.setText(cursor.getString(cursor.getColumnIndex(key.getCity())));
                contact.setText(cursor.getString(cursor.getColumnIndex(key.getContact())));
            }
            do{
            if(employ.getString(employ.getColumnIndex(emp.getId())).equals(id)){
                String post = employ.getString(employ.getColumnIndex(emp.getPosition()))+" at "+employ.getString(employ.getColumnIndex(emp.getCompany_name()));
                company.setText(post);
                exp.setText(employ.getString(employ.getColumnIndex(emp.getDuration())));
                break;
            }
            }while (employ.moveToNext());
            do {
                if (edu.getString(edu.getColumnIndex(educationTable.getId())).equals(id)) {
                    if (edu.getString(edu.getColumnIndex(educationTable.getType())).equalsIgnoreCase("graduation")) {
                        clg.setText(edu.getString(edu.getColumnIndex(educationTable.getInfo())));
                    }
                    if (edu.getString(edu.getColumnIndex(educationTable.getType())).equalsIgnoreCase("school")) {
                        schl.setText(edu.getString(edu.getColumnIndex(educationTable.getInfo())));
                    }
                }
            }while (edu.moveToNext());
    }
}
