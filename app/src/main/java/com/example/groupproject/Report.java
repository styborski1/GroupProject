package com.example.groupproject;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "report_table")
public class Report {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "title")
    private String title;

    private String description;
    private String city;
    private String temperature;



    public Report(@NonNull String title, String description, String city, String temperature ) {
        this.title = title;
        this.description = description;
        this.city = city;
        this.temperature = temperature;
    }


    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getCity() {
        return city;
    }
    public String getTemperature() {
        return temperature;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setTemperature(String temperature){
        this.temperature = temperature;
    }

}
