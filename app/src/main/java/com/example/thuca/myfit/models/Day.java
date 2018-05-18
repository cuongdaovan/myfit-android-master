package com.example.thuca.myfit.models;

import java.util.List;

public class Day {
    private int day;
    private int month;
    private int year;
    private List<Schedule> period_1;
    private List<Schedule> period_2;
    private List<Schedule> period_3;
    private List<Schedule> period_4;
    private List<Schedule> period_5;

    public Day(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Schedule> getPeriod_1() {
        return period_1;
    }

    public void setPeriod_1(List<Schedule> period_1) {
        this.period_1 = period_1;
    }

    public List<Schedule> getPeriod_2() {
        return period_2;
    }

    public void setPeriod_2(List<Schedule> period_2) {
        this.period_2 = period_2;
    }

    public List<Schedule> getPeriod_3() {
        return period_3;
    }

    public void setPeriod_3(List<Schedule> period_3) {
        this.period_3 = period_3;
    }

    public List<Schedule> getPeriod_4() {
        return period_4;
    }

    public void setPeriod_4(List<Schedule> period_4) {
        this.period_4 = period_4;
    }

    public List<Schedule> getPeriod_5() {
        return period_5;
    }

    public void setPeriod_5(List<Schedule> period_5) {
        this.period_5 = period_5;
    }
}
