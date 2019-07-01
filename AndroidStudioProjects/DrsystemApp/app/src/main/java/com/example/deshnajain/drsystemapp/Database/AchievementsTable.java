package com.example.deshnajain.drsystemapp.Database;

import android.database.sqlite.SQLiteDatabase;

public class AchievementsTable {
    private String tableName="Achievement";
    private String ach_id="ach_id";
    private String id = "id";
    private String info="info";
    private String year="year";

    public AchievementsTable(SQLiteDatabase sqLiteDatabase){
        String sql="CREATE TABLE "+tableName+"("+
                ""+ach_id+" INTEGER PRIMARY KEY , AUTO_INCREMENT ,"+
                id +" INTEGER ,"+
                ""+info+" VARCHAR(100) ,"+
                ""+year+" VARCHAR(50) ,"+
                "CONSTRAINT fk_ach FOREIGN KEY (id) REFERENCES user(cus_id) ON DELETE CASCADE ON UPDATE CASCADE);";
        sqLiteDatabase.execSQL(sql);

    }
public AchievementsTable(){

}
    public AchievementsTable(String id, String info, String year) {
        this.id = id;
        this.info = info;
        this.year = year;
    }

    public String getTableName() {
        return tableName;
    }

    public String getAch_id() {
        return ach_id;
    }

    public void setAch_id(String ed_id) {
        this.ach_id = ach_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
