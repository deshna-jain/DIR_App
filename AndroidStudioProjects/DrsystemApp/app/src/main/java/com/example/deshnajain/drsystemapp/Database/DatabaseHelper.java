package com.example.deshnajain.drsystemapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String dbName="dir";
    private static DatabaseHelper databaseHelper;

    private DatabaseHelper(Context context) {
        super(context, dbName, null, 1);
    }
public static  DatabaseHelper getInstance(Context context){
        if(databaseHelper==null){
            return databaseHelper=new DatabaseHelper(context);
        }else {
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
    public void addUserData(UserTable userTable){
        UserTable keyUserName = new UserTable();
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(keyUserName.getBranch(),userTable.getBranch());
        contentValues.put(keyUserName.getF_name(),userTable.getF_name());
        contentValues.put(keyUserName.getL_name(),userTable.getL_name());
        contentValues.put(keyUserName.getEmail(),userTable.getEmail());
        contentValues.put(keyUserName.getGender(),userTable.getGender());
        contentValues.put(keyUserName.getCity(),userTable.getCity());
        contentValues.put(keyUserName.getContact(),userTable.getContact());
        contentValues.put(keyUserName.getPassword(),userTable.getPassword());
        contentValues.put(keyUserName.getDob(),userTable.getDob());
        //sqLiteDatabase.insert(keyUserName.getTableName(),)
    }
}
