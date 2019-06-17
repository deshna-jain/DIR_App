package com.example.deshnajain.drsystemapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mfirstName;
    private EditText mlastName;
    private EditText mcity;
    private EditText mpin;
    private EditText memail;
    private EditText mcontact;
    private Button mnxtbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button btn=findViewById(R.id.bckbtn);
        mfirstName= (EditText) findViewById(R.id.fname);
        mlastName= (EditText) findViewById(R.id.lname);
        mcity= (EditText) findViewById(R.id.cityname);
        mpin= (EditText) findViewById(R.id.pin);
        memail= (EditText) findViewById(R.id.email);
        mcontact= (EditText) findViewById(R.id.contact);
        mnxtbtn= (Button) findViewById(R.id.Next);
        btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        backToMainScreen();
                    }
        });
        mnxtbtn.setOnClickListener(this);


        try{
            String s = getIntent().getExtras().getString(UtilsClass.NAME_LOGIN);
            String ss = getIntent().getStringExtra(UtilsClass.NAME_LOGIN);
            Toast.makeText(RegisterActivity.this,ss,Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(RegisterActivity.this,"Error occur!",Toast.LENGTH_LONG).show();
        }
    }

    private void backToMainScreen() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    private String TAG="SGSITS";
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Next:
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
        }

    }
    }
