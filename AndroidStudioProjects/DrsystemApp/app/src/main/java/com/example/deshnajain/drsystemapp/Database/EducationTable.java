package com.example.deshnajain.drsystemapp.Database;

import android.database.sqlite.SQLiteDatabase;

public class EducationTable {
    private String tableName="education_detail";
    private String ed_id="ed_id";
    private String id = "id";
    private String info="info";
    private String type="type";
    private String duration="duration";

    public EducationTable(SQLiteDatabase sqLiteDatabase){
        String sql="CREATE TABLE "+tableName+"("+
                ""+ed_id+" INTEGER PRIMARY KEY , AUTO_INCREMENT ,"+
    id +" INTEGER ,"+
     ""+info+" VARCHAR(100) ,"+
     ""+type+" VARCHAR(50) ,"+
     ""+duration+" VARCHAR(50) ,"+
     "CONSTRAINT fk_user FOREIGN KEY (id) REFERENCES user(cus_id) ON DELETE CASCADE ON UPDATE CASCADE);";
        sqLiteDatabase.execSQL(sql);

    }
}
