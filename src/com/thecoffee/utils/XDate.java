/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thecoffee.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author daung
 */
public class XDate {

    public static int getTotalDayInMonth(int month, int year) {
        int total = 30;

        if (month == 2) {
            if (year % 100 == 0 && year % 4 == 0) {
                total = 29;
            } else if (year % 4 == 0 && month == 2) {
                total = 29;
            } else {
                total = 28;
            }
        }

        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            total = 31;
        }

        return total;
    }

    public static boolean diLamMuon(Date d) {
        SimpleDateFormat sdfHour = new SimpleDateFormat("kk");
        SimpleDateFormat sdfMin = new SimpleDateFormat("mm");
        return Integer.parseInt(sdfHour.format(d)) >= 7 && Integer.parseInt(sdfMin.format(d)) > 0 || Integer.parseInt(sdfHour.format(d)) > 7;
    }
}
