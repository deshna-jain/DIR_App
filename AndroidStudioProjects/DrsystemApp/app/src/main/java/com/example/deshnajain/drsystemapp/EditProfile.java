package com.example.deshnajain.drsystemapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.deshnajain.drsystemapp.Database.AchievementsTable;
import com.example.deshnajain.drsystemapp.Database.DatabaseHelper;
import com.example.deshnajain.drsystemapp.Database.EducationTable;
import com.example.deshnajain.drsystemapp.Database.EmploymentTable;
import com.example.deshnajain.drsystemapp.Database.SkillsTable;
import com.example.deshnajain.drsystemapp.Database.UserTable;

import java.util.Calendar;

public class EditProfile extends AppCompatActivity {
    private Spinner etype;
    private Spinner etype1;
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
    DatePickerDialog.OnDateSetListener setListener;

    private DatabaseHelper databaseHelper;

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

        UserTable edit = new UserTable(email.getText().toString(), fname.getText().toString(), lname.getText().toString(),"Female", city.getText().toString(), contact.getText().toString(),pass, getDateEt.getText().toString());
        AchievementsTable achievementsTable = new AchievementsTable("1", ach.getText().toString(), yr.getText().toString());
        EducationTable educationTable = new EducationTable("1", inst1.getText().toString(), etype.getSelectedItem().toString());
        EducationTable educationTable1 = new EducationTable("1", inst2.getText().toString(), etype1.getSelectedItem().toString());
        EmploymentTable employmentTable = new EmploymentTable("1", company.getText().toString(), getjDate.getText().toString(), post.getText().toString(), dur.getText().toString());
        SkillsTable skillsTable = new SkillsTable("1", skill.getText().toString(), stype.getText().toString());
        if (databaseHelper.editUserData(edit) && (databaseHelper.addAchievement(achievementsTable)) &&(databaseHelper.addEducation(educationTable))&&(databaseHelper.addEducation(educationTable1))&&(databaseHelper.addEmployement(employmentTable))&&(databaseHelper.addSkills(skillsTable))) {
            Toast.makeText(this, "Edit Successful", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Not Edited", Toast.LENGTH_LONG).show();
        }
    }
}



