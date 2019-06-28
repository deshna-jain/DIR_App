package com.example.deshnajain.drsystemapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.deshnajain.drsystemapp.Database.UserTable;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mfirstName;
    private EditText mlastName;
    private EditText mcity;
    private EditText mpin;
    private EditText memail;
    private EditText mcontact;
    private Button submitbtn;
    private Calendar calendar=Calendar.getInstance();
    private int year, month, day;
    private EditText getDateEt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Button btn=findViewById(R.id.bckbtn);
        mfirstName= (EditText) findViewById(R.id.fname);
        mlastName= (EditText) findViewById(R.id.lname);
        mcity= (EditText) findViewById(R.id.cityname);
        mpin= (EditText) findViewById(R.id.pin);
        memail= (EditText) findViewById(R.id.email);
        mcontact= (EditText) findViewById(R.id.contact);
        submitbtn= (Button) findViewById(R.id.submitbtn);
        submitbtn.setOnClickListener(this);
        getDateEt=findViewById(R.id.dob);


        try{
            String s = getIntent().getExtras().getString(UtilsClass.NAME_LOGIN);
            String ss = getIntent().getStringExtra(UtilsClass.NAME_LOGIN);
            Toast.makeText(RegisterActivity.this,ss,Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(RegisterActivity.this,"Error occur!",Toast.LENGTH_LONG).show();
        }
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
    }
    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        getDateEt.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
    private void backToMainScreen() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    private String TAG="SGSITS";
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submitbtn:
                Log.i(TAG,"Next button clicked...");
                validateRegisterScreen();
                break;
    }
}

    private void validateRegisterScreen() {
        if(mfirstName.getText().toString().isEmpty()){
            Log.i(TAG,"Registration Failed Please provide first name...");
            Toast.makeText(this,"Please provide first name.",Toast.LENGTH_LONG).show();
        }else if(mlastName.getText().toString().isEmpty()){
            Log.i(TAG,"Registration Failed Please provide lastname...");
            Toast.makeText(this,"Please provide lastname.",Toast.LENGTH_LONG).show();
        }else if(mcity.getText().toString().isEmpty()){
            Log.i(TAG,"Registration Failed Please provide cityname...");
            Toast.makeText(this,"Please provide City.",Toast.LENGTH_LONG).show();
        }else if(mpin.getText().toString().isEmpty()){
            Log.i(TAG,"Registration Failed Please provide pincode...");
            Toast.makeText(this,"Please provide pincode.",Toast.LENGTH_LONG).show();
        }else if(memail.getText().toString().isEmpty()){
            Log.i(TAG,"Registration Failed Please provide email id...");
            Toast.makeText(this,"Please provide email.",Toast.LENGTH_LONG).show();
        }else if(mcontact.getText().toString().isEmpty()){
            Log.i(TAG,"Registration Failed Please provide contact no...");
            Toast.makeText(this,"Please provide contact no.",Toast.LENGTH_LONG).show();
        }else{
            Log.i(TAG,"Registration Success...");
            Toast.makeText(this,"Registration Success.",Toast.LENGTH_LONG).show();
            //UserTable inputData
        }

        }
    }

