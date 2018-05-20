package com.victorjavier.workbook;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class LogicCalendar {
    public static int getFirstWeekDay(){
        Calendar cal = new GregorianCalendar();
        return cal.get(Calendar.DAY_OF_WEEK);
    }
    public static int getFirstWeekDay(Date date){
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }
    public static int getMonthDaysNumber(int month, int year){
        int daysNumber = 0;
        switch(month){
            case 1:
                daysNumber = 31;
                break;
            case 2:
                if (((year%100 ==0) && (year%400 == 0)) || ((year%100 != 0) && (year%4 == 0))){
                    daysNumber = 29;
                }else{
                    daysNumber = 28;
                }
                break;
            case 3:
                daysNumber = 31;
                break;
            case 4:
                daysNumber = 30;
                break;
            case 5:
                daysNumber = 31;
                break;
            case 6:
                daysNumber = 30;
                break;
            case 7:
                daysNumber = 31;
                break;
            case 8:
                daysNumber = 31;
                break;
            case 9:
                daysNumber = 30;
                break;
            case 10:
                daysNumber = 31;
                break;
            case 11:
                daysNumber = 30;
                break;
            case 12:
                daysNumber = 31;
                break;
        }
        return daysNumber;
    }
}
