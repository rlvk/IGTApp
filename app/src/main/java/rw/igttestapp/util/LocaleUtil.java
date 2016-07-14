package rw.igttestapp.util;

import android.content.Context;

import java.text.NumberFormat;
import java.util.Locale;

import rw.igttestapp.R;

/**
 * Created by rafalwesolowski on 14/07/2016.
 */
public class LocaleUtil {

    /**
     * Returns string for jackpot.
     *
     * @param context       Context
     * @param currency      Currency type
     * @param jackpotNumber Jackpot number
     * @return String representing date in specified format
     */
    public static String getJackpotAmount(Context context, String currency, String jackpotNumber) {
        if (jackpotNumber == null) {
            return "";
        }
        try {
            int jackpotIntegerNumber = Integer.valueOf(jackpotNumber);
            NumberFormat format;
            if (currency.equals(context.getString(R.string.gbp))) {
                format = NumberFormat.getCurrencyInstance(Locale.UK);
            } else if (currency.equals(context.getString(R.string.usd))) {
                format = NumberFormat.getCurrencyInstance(Locale.US);
            } else {
                format = NumberFormat.getCurrencyInstance();
            }

            return format.format(jackpotIntegerNumber);
        } catch (NumberFormatException ex) {
            return "";
        }
    }
}
