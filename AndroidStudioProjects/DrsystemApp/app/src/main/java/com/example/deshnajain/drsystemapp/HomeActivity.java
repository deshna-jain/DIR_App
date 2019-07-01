package com.example.deshnajain.drsystemapp;;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuInflater;
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
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import com.example.deshnajain.drsystemapp.Adp.RecyclerViewAdp;
import com.example.deshnajain.drsystemapp.Database.DatabaseHelper;
import com.example.deshnajain.drsystemapp.Database.NotificationTable;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView;
    private DatabaseHelper databaseHelper;

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
        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this);
        Cursor cursor = databaseHelper.getDataFromNotification();
        NotificationTable key = new NotificationTable();


        ArrayList<NotificationTable> strings = new ArrayList<>();
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                do {
                    String id = cursor.getString(cursor.getColumnIndex(key.getId()));
                    String title = cursor.getString(cursor.getColumnIndex(key.getTitle()));
                    String des = cursor.getString(cursor.getColumnIndex(key.getDes()));
                    String domain = cursor.getString(cursor.getColumnIndex(key.getDomain()));
                    String branch = cursor.getString(cursor.getColumnIndex(key.getBranch()));
                    String summary = cursor.getString(cursor.getColumnIndex(key.getSummary()));
                    String srt_date = cursor.getString(cursor.getColumnIndex(key.getSrt_date()));
                    String end_date = cursor.getString(cursor.getColumnIndex(key.getEnd_date()));
                    String sem = cursor.getString(cursor.getColumnIndex(key.getSem()));
                    strings.add(
                            new NotificationTable(id, title, domain, srt_date, end_date, des, summary, branch, sem)
                    );
                } while (cursor.moveToNext());
            }
        }
        RecyclerViewAdp recyclerViewAdp = new RecyclerViewAdp(this, strings);
        recyclerView.setAdapter(recyclerViewAdp);

        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNotification();

            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.HOUR_OF_DAY, 12);
        calendar.set(calendar.MINUTE, 0);
        calendar.set(calendar.SECOND, 0);
        calendar.set(calendar.MILLISECOND, 0);
        setAlarm(calendar);
        SharedPreferences sharedPreferences = getSharedPreferences("DrsystemApp", Context.MODE_PRIVATE);
        Toast.makeText(this, sharedPreferences.getString("SKeyUser", ""), Toast.LENGTH_LONG).show();

    }

    private void createNotification() {
        Intent intent = new Intent(this, NotificationActivity.class);
        startActivity(intent);
    }

    private void setAlarm(Calendar targetCal) {
        //textAlarmPrompt.setText("\n\n***\n"+"Älarm is set"+targetCal.getTime()+"\n"+"***\n");
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);


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
searchUsers();
        } else if (id == R.id.nav_logout) {
            this.finish();
            Toast.makeText(this, "Logged out", Toast.LENGTH_LONG).show();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.abtus) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void searchUsers() {
        Intent intent = new Intent(this,SearchActivity.class);
        startActivity(intent);
    }

    private void editProfileView() {
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
    }

    private void startProfileView() {
        Intent intent = new Intent(this, ProfileView.class);
        startActivity(intent);
    }
}

