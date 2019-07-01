package com.example.deshnajain.drsystemapp.Database;

import android.database.sqlite.SQLiteDatabase;

public class NotificationTable {
    private String tableName="notification";
    private String not_id="not_id";
    private String id = "id";
    private String des="des";
    private String title="title";
    private String srt_date="srt_date";
    private String end_date ="end_date";
    private String domain="domain";
    private String branch="branch";
    private String sem="sem";
    private String summary="summary";

    public NotificationTable(SQLiteDatabase sqLiteDatabase){
        String sql="CREATE TABLE "+tableName+"("+
                ""+not_id+" INTEGER PRIMARY KEY , AUTO_INCREMENT ,"+
                ""+id +" INTEGER ,"+
                ""+title+" VARCHAR(50) ,"+
                ""+domain+" VARCHAR(100) ,"+
                ""+srt_date+" DATE ,"+
                ""+end_date+" DATE ,"+
                ""+des+" VARCHAR(200) ,"+
                ""+summary+" VARCHAR(100) ,"+
                ""+branch+" VARCHAR(20) ,"+
                ""+sem+" VARCHAR(20) ,"+
                "CONSTRAINT fk_user FOREIGN KEY (id) REFERENCES user(cus_id) ON DELETE CASCADE ON UPDATE CASCADE);";
        sqLiteDatabase.execSQL(sql);

    }
    public NotificationTable(){

    }

    public NotificationTable(String id, String title, String domain, String srt_date, String end_date, String des, String summary, String branch, String sem) {
        this.des = des;
        this.id=id;
        this.title = title;
        this.srt_date = srt_date;
        this.end_date = end_date;
        this.domain = domain;
        this.branch = branch;
        this.sem = sem;
        this.summary = summary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDes() {
        return des;
    }

    public String getTitle() {
        return title;
    }

    public String getSrt_date() {
        return srt_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public String getDomain() {
        return domain;
    }

    public String getBranch() {
        return branch;
    }

    public String getSem() {
        return sem;
    }

    public String getSummary() {
        return summary;
    }

    public String getTableName() {
        return tableName;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSrt_date(String srt_date) {
        this.srt_date = srt_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
