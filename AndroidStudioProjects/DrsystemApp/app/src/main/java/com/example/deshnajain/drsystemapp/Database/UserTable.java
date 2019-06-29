package com.example.deshnajain.drsystemapp.Database;

import android.database.sqlite.SQLiteDatabase;

public class UserTable {
    private String tableName="user";
    private String id="cus_id";
    private String email="email";
    private String f_name="f_name";
    private String l_name="l_name";
    private String gender = "gender";
    private String city = "city";
    private String contact = "contact";
    private String password = "password";
    private String dob = "dob";
   // private String branch = "branch";

    public UserTable(SQLiteDatabase sqLiteDatabase){
        String sql="CREATE TABLE "+tableName+"("+
                ""+id+" INTEGER PRIMARY KEY , AUTO_INCREMENT ,"+
                ""+email+" VARCHAR(50) ,"+
                ""+f_name+" VARCHAR(50) ,"+
                ""+l_name+" VARCHAR(50) ,"+
                ""+gender+" VARCHAR(10) ,"+
                ""+city+" VARCHAR(50) ,"+
                ""+contact+" BIGINT(20) ,"+
                ""+password+" VARCHAR(50) ,"+
                ""+dob+" DATE );";
        sqLiteDatabase.execSQL(sql);

    }
    public UserTable(){

    }
    public UserTable(String email, String f_name, String l_name, String gender, String city, String contact, String password, String dob) {
        this.email = email;
        this.f_name = f_name;
        this.l_name = l_name;
        this.gender = gender;
        this.city = city;
        this.contact = contact;
        this.password = password;
        this.dob = dob;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getF_name() {
        return f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public String getGender() {
        return gender;
    }

    public String getCity() {
        return city;
    }

    public String getContact() {
        return contact;
    }

    public String getPassword() {
        return password;
    }

    public String getDob() {
        return dob;
    }

    //public String getBranch() {
        //return branch;
   // }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

   /* public void setBranch(String branch) {
        this.branch = branch;
    }*/
    public String getTableName(){
        return tableName;
    }
}
