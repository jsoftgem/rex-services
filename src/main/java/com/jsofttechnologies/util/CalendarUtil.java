package com.jsofttechnologies.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jerico on 2/20/2015.
 */
public class CalendarUtil {

    public static Integer getWeek(Date date) {
        int week = 4;
        Calendar activityCal = Calendar.getInstance();

        activityCal.setTime(date);


        Calendar prevMonthCal = Calendar.getInstance();

        int month = activityCal.get(Calendar.MONTH);

        if (month > 0) {
            prevMonthCal.set(Calendar.MONTH, activityCal.get(Calendar.MONTH) - 1);
        } else {
            week = 4;
            prevMonthCal.set(Calendar.MONTH, activityCal.get(Calendar.MONTH));

        }

        prevMonthCal.set(Calendar.DAY_OF_MONTH, 1);
        prevMonthCal.set(Calendar.YEAR, activityCal.get(Calendar.YEAR));

        int[] weeks = {1, 2, 3, 4, 5};

        int preMonthDate = prevMonthCal.get(Calendar.DAY_OF_YEAR);
        int activityDate = activityCal.get(Calendar.DAY_OF_YEAR);

        Calendar testCal = Calendar.getInstance();

        testCal.set(Calendar.YEAR, activityCal.get(Calendar.YEAR));

        for (int i = preMonthDate;
             i <= activityDate; i++) {

            testCal.set(Calendar.DAY_OF_YEAR, i);

            if (testCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                continue;
            }

            if (testCal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {

                if (week < testCal.get(Calendar.WEEK_OF_MONTH)) {
                    week++;
                } else {
                    week = 0;
                }

            }

            if (testCal.get(Calendar.DAY_OF_YEAR) == activityCal.get(Calendar.DAY_OF_YEAR)) {
                break;
            }

        }

        if (month == 0 && testCal.get(Calendar.WEEK_OF_MONTH) == 1 && (testCal.get(Calendar.DAY_OF_MONTH) >= 1 && testCal.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY)) {
            Calendar prevYear = Calendar.getInstance();
            prevYear.set(Calendar.YEAR, testCal.get(Calendar.YEAR) - 1);
            prevYear.set(Calendar.DAY_OF_MONTH, 31);
            prevYear.set(Calendar.MONTH, Calendar.DECEMBER);

            return getWeek(prevYear.getTime());
        }

        return weeks[week];
    }
}
