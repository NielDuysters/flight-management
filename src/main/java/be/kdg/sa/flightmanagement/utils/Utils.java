package be.kdg.sa.flightmanagement.utils;

import java.util.Date;

/**
 * @author LukasVandenBossche on 06/10/2021
 * @project flightmanagement
 */
public class Utils {
    public static Date calculateDate(Date now, String day, String time) {
        Date nowAddTwelve = new Date(), nowSubTwelve = new Date();
        nowAddTwelve.setTime(now.getTime() + 12 * 60 * 60 * 1000);
        nowSubTwelve.setTime(now.getTime() - 12 * 60 * 60 * 1000);

        Date calculated = new Date(now.getYear(), now.getMonth(), Integer.parseInt(day), Integer.parseInt(time.substring(0, 2)), Integer.parseInt(time.substring(2, 4)));
        while (true) {
            if (calculated.before(nowSubTwelve)) {
                calculated.setTime(calculated.getTime() + 24 * 60 * 60 * 1000);
            } else if (calculated.after(nowAddTwelve)) {
                calculated.setTime(calculated.getTime() - 24 * 60 * 60 * 1000);
            } else {
                return calculated;
            }
        }
    }

    public static Date addMinutesToDate(Date date, int minutes) {
        long milliseconds = date.getTime();
        milliseconds += (long) minutes * 60 * 1000;
        date.setTime(milliseconds);
        return date;
    }
}


