package com.example.deshnajain.drsystemapp.Database;

import android.database.sqlite.SQLiteDatabase;

public class SkillsTable {
    private String tableName="skills";
    private String ed_id="ed_id";
    private String id = "id";
    private String info="info";
    private String type="type";

    public SkillsTable(SQLiteDatabase sqLiteDatabase){
        String sql="CREATE TABLE "+tableName+"("+
                ""+ed_id+" INTEGER PRIMARY KEY , AUTO_INCREMENT ,"+
                id +" INTEGER ,"+
                ""+info+" VARCHAR(100) ,"+
                ""+type+" VARCHAR(50) ,"+
                "CONSTRAINT fk_ed FOREIGN KEY (id) REFERENCES user(cus_id) ON DELETE CASCADE ON UPDATE CASCADE);";
        sqLiteDatabase.execSQL(sql);

    }
    public SkillsTable(){

    }

    public SkillsTable(String id, String info, String type) {
        this.id = id;
        this.info = info;
        this.type = type;
    }

    public String getTableName() {
        return tableName;
    }

    public String getEd_id() {
        return ed_id;
    }

    public void setEd_id(String ed_id) {
        this.ed_id = ed_id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
