package com.jsofttechnologies.rexwar.util.contants;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jerico on 1/28/2015.
 */
public enum Month {


    JANUARY("January", "JAN", Calendar.JANUARY), FEBRUARY("February", "FEB", Calendar.FEBRUARY), MARCH("March", "MAR", Calendar.MARCH), APRIL("April", "APR", Calendar.APRIL), MAY("May", "MAY", Calendar.MAY),
    JUNE("June", "JUN", Calendar.JUNE), JULY("July", "JUL", Calendar.JULY), AUGUST("August", "AUG", Calendar.AUGUST), SEPTEMBER("September", "SEP", Calendar.SEPTEMBER), OCTOBER("October", "OCT", Calendar.OCTOBER),
    NOVEMBER("November", "NOV", Calendar.NOVEMBER), DECEMBER("December", "DEC", Calendar.DECEMBER);


    private int calendar;
    private String label;
    private String shortLabel;


    Month(String label, String shortLabel, int calendar) {
        this.calendar = calendar;
        this.label = label;
        this.shortLabel = shortLabel;
    }

    public Date getAsDate() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(Calendar.MONTH, getCalendar());
        calendar1.set(Calendar.DAY_OF_MONTH, 1);
        calendar1.set(Calendar.HOUR_OF_DAY, 1);
        calendar1.set(Calendar.MINUTE, 0);
        calendar1.set(Calendar.SECOND, 0);
        return calendar1.getTime();
    }

    public String getLabel() {
        return label;
    }

    public String getShortLabel() {
        return shortLabel;
    }

    public int getCalendar() {
        return calendar;
    }


    public static Month getMonth(int calendar) {
        Month month = null;
        switch (calendar) {
            case Calendar.JANUARY:
                month = JANUARY;
                break;
            case Calendar.FEBRUARY:
                month = FEBRUARY;
                break;
            case Calendar.MARCH:
                month = MARCH;
                break;
            case Calendar.APRIL:
                month = APRIL;
                break;
            case Calendar.MAY:
                month = MAY;
                break;
            case Calendar.JUNE:
                month = JUNE;
                break;
            case Calendar.JULY:
                month = JULY;
                break;
            case Calendar.AUGUST:
                month = AUGUST;
                break;
            case Calendar.SEPTEMBER:
                month = SEPTEMBER;
                break;
            case Calendar.OCTOBER:
                month = OCTOBER;
                break;
            case Calendar.NOVEMBER:
                month = NOVEMBER;
                break;
            case Calendar.DECEMBER:
                month = DECEMBER;
                break;
        }

        return month;
    }
}
