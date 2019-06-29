package com.example.deshnajain.drsystemapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deshnajain.drsystemapp.Database.DatabaseHelper;
import com.example.deshnajain.drsystemapp.Fragment.HelloFragment;

import static com.example.deshnajain.drsystemapp.R.id.LoginBtn;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mNotLoginTv;
    private EditText mUserNameEt;
    private EditText mpasswordEt;
    private Button mLoginBtn;
    private TextView fpass;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_1);

        mNotLoginTv= (TextView) findViewById(R.id.not_register);
        mUserNameEt= (EditText) findViewById(R.id.userNameEt);
        mpasswordEt= (EditText) findViewById(R.id.passwordEt);
        mLoginBtn= (Button) findViewById(LoginBtn);
        fpass=(TextView) findViewById(R.id.forgetPass);
        fpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFogotPasswordScreen();
            }
        });
        mNotLoginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegisterSccreen();
            }


    });
        mLoginBtn.setOnClickListener(this);

    }

    private void startFogotPasswordScreen() {
        Intent intent= new Intent(this, ForgotPass.class);
        startActivity(intent);
    }


    private void startRegisterSccreen(){
        Intent intent = new Intent(this,RegisterActivity.class);
        intent.putExtra(UtilsClass.NAME_LOGIN,"SGSITS coming..");
        startActivity(intent);
    }
    private String TAG="SGSITS";

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case LoginBtn:
                /*FragmentManager fragmentManager=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
                HelloFragment hello = new HelloFragment();
                fragmentTransaction.add(R.id.fragment,hello, "Hello").addToBackStack("Hello").commit();*/
                Log.i(TAG,"Login button clicked...");
                validateLoginScreen();
                break;
        }
    }

    private void validateLoginScreen(){
        if(mUserNameEt.getText().toString().isEmpty()){
            Log.i(TAG,"Login Failed Please provide user name...");
            Toast.makeText(this,"Please provide user name.",Toast.LENGTH_LONG).show();
        }else if(mpasswordEt.getText().toString().isEmpty()){
            Log.i(TAG,"Login Failed Please provide password...");
            Toast.makeText(this,"Please provide password.",Toast.LENGTH_LONG).show();
        }else{
            checkDatabase();


        } /*if((mUserNameEt.getText().toString().equals("example@gmail.com"))&&(mpasswordEt.getText().toString().equals("123456"))) {
            Log.i(TAG, "valid user");
            Toast.makeText(this, "Login success", Toast.LENGTH_LONG).show();
            SharedPreferences sharedPreferences= getSharedPreferences("DrsystemApp",Context.MODE_PRIVATE);
            sharedPreferences.edit().putString("SKey",""+mUserNameEt.getText().toString()).commit();

        }else{
            Log.i(TAG,"Login Failed,invalid user...");
            Toast.makeText(this,"Login failed, invalid user.",Toast.LENGTH_LONG).show();
        }*/
    }

    private void checkDatabase() {
       databaseHelper= DatabaseHelper.getInstance(this);
        Cursor cursor = databaseHelper.getDataFromUser();
        cursor.moveToFirst();
        do{
            String email = cursor.getString(2);
            String pass = cursor.getString(8);

            if(mUserNameEt.getText().toString().equalsIgnoreCase(email)){
                if(mpasswordEt.getText().toString().equalsIgnoreCase(pass)){
                    startActivity(new Intent(this, HomeActivity.class));
                    SharedPreferences sharedPreferences= getSharedPreferences("DrsystemApp",Context.MODE_PRIVATE);
                    sharedPreferences.edit().putString("SKeyUser",""+mUserNameEt.getText().toString()).commit();
                    sharedPreferences.edit().putString("SKeyPass",""+mpasswordEt.getText().toString()).commit();

                }else {
                    Toast.makeText(this, "Login failed, invalid password.", Toast.LENGTH_LONG).show();
                }
            }else {
                Toast.makeText(this,"Login failed, invalid username.",Toast.LENGTH_LONG).show();
            }
        }while (cursor.moveToNext());
        //cursor.close();
        //databaseHelper.close();
    }

   /* private void gotoHomeScreen() {
        Intent intent = new Intent(this,HomeActivity.class);
        intent.putExtra(UtilsClass.NAME_LOGIN,"Welcome!!");
        startActivity(intent);
    }*/


}
