package com.example.deshnajain.drsystemapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ForgotPass extends AppCompatActivity {
private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        btn = findViewById(R.id.esent);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               verified();
            }
        });



    }

    private void verified() {
        Toast.makeText(this,"Email sent",Toast.LENGTH_LONG).show();
    }
}
