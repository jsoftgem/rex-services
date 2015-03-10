package com.jsofttechnologies.rexwar.util.contants;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Jerico on 1/28/2015.
 */
public enum Month {


    JANUARY("January", Calendar.JANUARY), FEBRUARY("February", Calendar.FEBRUARY), MARCH("March", Calendar.MARCH), APRIL("April", Calendar.APRIL), MAY("May", Calendar.MAY),
    JUNE("June", Calendar.JUNE), JULY("July", Calendar.JULY), AUGUST("August", Calendar.AUGUST), SEPTEMBER("September", Calendar.SEPTEMBER), OCTOBER("October", Calendar.OCTOBER),
    NOVEMBER("November", Calendar.NOVEMBER), DECEMBER("December", Calendar.DECEMBER);


    private int calendar;
    private String label;


    Month(String label, int calendar) {
        this.calendar = calendar;
        this.label = label;
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

    public int getCalendar() {
        return calendar;
    }
}
