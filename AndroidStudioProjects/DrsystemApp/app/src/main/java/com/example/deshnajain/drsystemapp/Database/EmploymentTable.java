package com.example.deshnajain.drsystemapp.Database;

import android.database.sqlite.SQLiteDatabase;

public class EmploymentTable {
    private String tableName = "employment";
    private String em_id = "em_id";
    private String id = "id";
    private String company_name = "company_name";
    private String join_date = "join_date";
    private String position = "position";
    private String duration = "duration";

    public EmploymentTable(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + tableName + "(" +
                "" + em_id + " INTEGER PRIMARY KEY , AUTO_INCREMENT ," +
                id + " INTEGER ," +
                "" + company_name + " VARCHAR(50) ," +
                "" + join_date + " DATE ," +
                "" + position + " VARCHAR(50) ," +
                "" + duration + " VARCHAR(50) ," +
                "CONSTRAINT fk_emp FOREIGN KEY (id) REFERENCES user(cus_id) ON DELETE CASCADE ON UPDATE CASCADE);";
        sqLiteDatabase.execSQL(sql);

    }

    public EmploymentTable() {

    }

    public String getTableName() {
        return tableName;
    }

    public EmploymentTable(String id, String company_name, String join_date, String position, String duration) {
        this.id = id;
        this.company_name = company_name;
        this.join_date = join_date;
        this.position = position;
        this.duration = duration;
    }

    public String getEm_id() {
        return em_id;
    }

    public void setEm_id(String em_id) {
        this.em_id = em_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getJoin_date() {
        return join_date;
    }

    public void setJoin_date(String join_date) {
        this.join_date = join_date;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}

