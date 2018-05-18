package com.example.thuca.myfit.helpers;

import android.content.res.Resources;
import android.view.View;

import com.example.thuca.myfit.R;

import java.util.Calendar;

/**
 * Created by iagomendesfucolo on 24/03/17.
 */

public class Util {

    private static int[] mDaysInMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };


    public static int getDayOfMonth(int month) {
        return mDaysInMonth[month];
    }

    public static int getVisibility(boolean flag){

        return flag ? View.VISIBLE : View.INVISIBLE;
    }


    public static boolean resolveDate(int monthDate, int actualMonth){

        return monthDate == actualMonth;
    }

    public static boolean dateEqual(int[] date, int day, int month, int year){
        return date[0] == day && date[1] == month && date[2] == year;
    }

    public static int getDay(int day) {
        switch (day) {
            case Calendar.MONDAY:
                return 0;
            case Calendar.TUESDAY:
                return 1;
            case Calendar.WEDNESDAY:
                return 2;
            case Calendar.THURSDAY:
                return 3;
            case Calendar.FRIDAY:
                return 4;
            case Calendar.SATURDAY:
                return 5;
            case Calendar.SUNDAY:
                return 6;
            default:
                return 0;
        }
    }

    public static String getMonth(int month) {
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
        }
        return "";
    }
}
