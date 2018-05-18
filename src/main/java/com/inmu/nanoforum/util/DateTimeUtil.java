package com.inmu.nanoforum.util;

import java.sql.Timestamp;
import java.util.Calendar;

public class DateTimeUtil {
    public static Timestamp getCurrentDateTime(){
        Calendar calendar = Calendar.getInstance();
        return new Timestamp(calendar.getTime().getTime());
    }
}
