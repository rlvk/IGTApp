package rw.igttestapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by rafalwesolowski on 14/07/2016.
 */
public class DateUtil {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    private static final String JACKPOT_DATE_FORMAT = "dd MMMM yyyy";

    // Expiration period 1 hour in miliseconds
    private static final int EXPIRY_PERIOD = 1000 * 60 * 60;

    /**
     * Return date in specified format.
     *
     * @param milliSeconds Date in milliseconds
     * @return String representing date in specified format
     */
    public static String getStringDate(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    private static Date getCachedUntil(String dateString) {
        if (dateString == null) {
            return null;
        }

        Date cacheUntil = null;

        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            cacheUntil = format.parse(dateString);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return cacheUntil;
    }

    /**
     * Tells whether created date string is expired or not.
     *
     * @param createdAt Date in milliseconds as String
     * @return String representing date in specified format
     */
    public static boolean isDataExpired(String createdAt) {
        if (createdAt == null) {
            return true;
        }

        Date cachedUntil = getCachedUntil(createdAt);
        if (cachedUntil == null) {
            return true;
        }

        Date expirationDate = new Date(cachedUntil.getTime() + EXPIRY_PERIOD);
        Date now = new Date();
        if (now.before(expirationDate)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Returns formatted date for jackpot.
     *
     * @param dateString Date as a String
     * @return String representing date in specified format
     */
    public static String getJackpotDate(String dateString) {
        if (dateString == null) {
            return null;
        }

        Date jackpotDate = new Date();

        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            jackpotDate = format.parse(dateString);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        SimpleDateFormat jackpotDateFormat = new SimpleDateFormat(JACKPOT_DATE_FORMAT);

        return jackpotDateFormat.format(jackpotDate);
    }
}
