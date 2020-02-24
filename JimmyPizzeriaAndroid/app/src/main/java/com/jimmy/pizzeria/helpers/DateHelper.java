package com.jimmy.pizzeria.helpers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateHelper {
    private static final long TICKS_AT_EPOCH = 621355968000000000L;
    private static final long TICKS_PER_MILLISECOND = 10000;

    public static Date getUTCDateNow() {
        return new Date(Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTimeInMillis());
    }

    public static long getTicksFromDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return (calendar.getTimeInMillis() * TICKS_PER_MILLISECOND) + TICKS_AT_EPOCH;
    }

    public static Date getDateFromTicks(long UTCTicks) {
        if (UTCTicks < 1) {
            return null;
        }
        return new Date((UTCTicks - TICKS_AT_EPOCH) / TICKS_PER_MILLISECOND);
    }

    public static String getFullFormattedDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    public static String getPrintDateTimeNow() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss", Locale.getDefault());
        return simpleDateFormat.format(new Date());
    }

    public static String getPrintDateNow() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        return simpleDateFormat.format(new Date());
    }
}
