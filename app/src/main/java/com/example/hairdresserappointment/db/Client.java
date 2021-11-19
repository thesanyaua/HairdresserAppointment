package com.example.hairdresserappointment.db;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "table_client")
public class Client {
    @PrimaryKey(autoGenerate = true)
    long id;
    int idDate;
    String name;
    String number_phone;
    int timeHour;
    int timeMinute;
    String job;

    public Client(long id, int idDate, String name, String number_phone, int timeHour, int timeMinute, String job) {
        this.id = id;
        this.idDate = idDate;
        this.name = name;
        this.number_phone = number_phone;
        this.timeHour = timeHour;
        this.timeMinute = timeMinute;
        this.job = job;
    }

    @Ignore
    public Client(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIdDate() {
        return idDate;
    }

    public void setIdDate(int idDate) {
        this.idDate = idDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber_phone() {
        return number_phone;
    }

    public void setNumber_phone(String number_phone) {
        this.number_phone = number_phone;
    }

    public int getTimeHour() {
        return timeHour;
    }

    public void setTimeHour(int timeHour) {
        this.timeHour = timeHour;
    }

    public int getTimeMinute() {
        return timeMinute;
    }

    public void setTimeMinute(int timeMinute) {
        this.timeMinute = timeMinute;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}



