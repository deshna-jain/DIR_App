package com.example.deshnajain.drsystemapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.deshnajain.drsystemapp.RegisterActivity;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String dbName = "dir";
    private static DatabaseHelper databaseHelper;
    private SQLiteDatabase myWritableDb;

    private DatabaseHelper(Context context) {
        super(context, dbName, null, 1);
    }

    public static DatabaseHelper getInstance(Context context) {
        if (databaseHelper == null) {
            return databaseHelper = new DatabaseHelper(context);
        } else {
            return databaseHelper;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        new UserTable(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }

    public Boolean addUserData(UserTable userTable) {
        UserTable keyUserName = new UserTable();
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            //contentValues.put(keyUserName.getBranch(), userTable.getBranch());
            contentValues.put(keyUserName.getEmail(), userTable.getEmail());
            contentValues.put(keyUserName.getF_name(), userTable.getF_name());
            contentValues.put(keyUserName.getL_name(), userTable.getL_name());
            contentValues.put(keyUserName.getGender(), userTable.getGender());
            contentValues.put(keyUserName.getCity(), userTable.getCity());
            contentValues.put(keyUserName.getContact(), userTable.getContact());
            contentValues.put(keyUserName.getPassword(), userTable.getPassword());
            contentValues.put(keyUserName.getDob(), userTable.getDob());
            sqLiteDatabase.insert(keyUserName.getTableName(), null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }
    public Cursor getDataFromUser(){
        SQLiteDatabase sqLiteDatabase=getMyWritableDatabase();
        UserTable userTable=new UserTable();
        return sqLiteDatabase.rawQuery("select * from "+userTable.getTableName(),null);

    }
    public Cursor getDataFromNotification(){
        SQLiteDatabase sqLiteDatabase=getMyWritableDatabase();
        NotificationTable notificationTable=new NotificationTable();
        return sqLiteDatabase.rawQuery("select * from "+notificationTable.getTableName(),null);

    }
    public SQLiteDatabase getMyWritableDatabase(){
        if((myWritableDb==null)||(!myWritableDb.isOpen())){
            myWritableDb= this.getWritableDatabase();
        }
        return  myWritableDb;
    }
    @Override
    public void close(){
        super.close();
        if(myWritableDb!=null){
            myWritableDb.close();
            myWritableDb=null;
        }
    }

    public boolean addNotificationData(NotificationTable notify) {
        NotificationTable keyNotify= new NotificationTable();
        try {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            //contentValues.put(keyUserName.getBranch(), userTable.getBranch());
            contentValues.put(keyNotify.getTitle(), notify.getTitle());
            contentValues.put(keyNotify.getDomain(), notify.getDomain());
            contentValues.put(keyNotify.getSrt_date(), notify.getSrt_date());
            contentValues.put(keyNotify.getEnd_date(), notify.getEnd_date());
            contentValues.put(keyNotify.getDes(), notify.getDes());
            contentValues.put(keyNotify.getSummary(), notify.getSummary());
            contentValues.put(keyNotify.getBranch(), notify.getBranch());
            contentValues.put(keyNotify.getSem(), notify.getSem());
            sqLiteDatabase.insert(keyNotify.getTableName(), null, contentValues);
            return true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }
}
