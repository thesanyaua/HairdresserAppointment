package com.example.hairdresserappointment.other;

public class DataInfo {
    String data;
    String dayName;
    String numberOfRecords;
    int openDate;


    public DataInfo(String data, String dayName, String numberOfRecords, int openDate) {
        this.data = data;
        this.dayName = dayName;
        this.numberOfRecords = numberOfRecords;
        this.openDate = openDate;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getNumberOfRecords() {
        return numberOfRecords;
    }

    public void setNumberOfRecords(String numberOfRecords) {
        this.numberOfRecords = numberOfRecords;
    }

    public int getOpenDate() {
        return openDate;
    }

    public void setOpenDate(int openDate) {
        this.openDate = openDate;
    }
}
