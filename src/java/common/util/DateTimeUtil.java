/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class DateTimeUtil {
     private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public static boolean checkDateFormat(String date) {
        try {
            formatter.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd;HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }

    public static Date convertToDate(String date) {
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertToString(Date date) {
        return formatter.format(date);
    }

    public static String convertToString(long date) {
        return formatter.format(new Date(date));
    }

    public static String getCurrentDay() {
        return formatter.format(new Date());
    }
    
    public static java.sql.Date getCurrentSqlDay(){
        String date = getCurrentDay();
        java.sql.Date sqlDate =  java.sql.Date.valueOf(date);
        return sqlDate;
    }
}
