package com.example.deshnajain.drsystemapp;;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import com.example.deshnajain.drsystemapp.Adp.RecyclerViewAdp;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView;
//private TextView mViewProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerView);


        /*Set up Linear layout for VERTICAL or HORIZONTAL Scrolling in Recycler view*/
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this,
                        LinearLayout.VERTICAL,
                        false));

        /*Set up Linear layout for VERTICAL or HORIZONTAL divider*/
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this,
                        LinearLayout.VERTICAL));

        ArrayList<String> strings = new ArrayList<>();
        strings.add("Student 1");
        strings.add("Student 2");
        strings.add("Student 3");
        strings.add("Student 4");
        strings.add("Student 5");
        strings.add("Student 6");
        strings.add("Student 7");
        strings.add("Student 8");
        RecyclerViewAdp recyclerViewAdp = new RecyclerViewAdp(this, strings);
        recyclerView.setAdapter(recyclerViewAdp);

        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        Calendar calendar= Calendar.getInstance();
        calendar.set(calendar.HOUR_OF_DAY,12);
        calendar.set(calendar.MINUTE,0);
        calendar.set(calendar.SECOND,0);
        calendar.set(calendar.MILLISECOND,0);
        setAlarm(calendar);
    }

    private void setAlarm(Calendar targetCal) {
        //textAlarmPrompt.setText("\n\n***\n"+"Älarm is set"+targetCal.getTime()+"\n"+"***\n");
        Intent intent= new Intent(getBaseContext(),AlarmReceiver.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(getBaseContext(),1,intent,0);
        AlarmManager alarmManager=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,targetCal.getTimeInMillis(),pendingIntent);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
                    startProfileView();
        } else if (id == R.id.nav_edit) {
            editProfileView();
        } else if (id == R.id.nav_search) {

        } else if (id == R.id.nav_logout) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.abtus) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void editProfileView() {
        Intent intent = new Intent(this,EditProfile.class);
        startActivity(intent);
    }

    private void startProfileView() {
        Intent intent = new Intent(this,ProfileView.class);
        startActivity(intent);
    }
}
