package com.example.deshnajain.drsystemapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {
private ImageButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        btn = findViewById(R.id.sendbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notification();
            }
        });
    }

    private void notification() {
        Toast.makeText(this,"Notification Sent",Toast.LENGTH_LONG).show();
        this.finish();
    }


    @Override
    public void onClick(View v) {

    }
}



