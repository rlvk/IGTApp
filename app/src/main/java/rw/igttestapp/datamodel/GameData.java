package rw.igttestapp.datamodel;

import android.content.Context;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import rw.igttestapp.util.DateUtil;
import rw.igttestapp.util.LocaleUtil;

/**
 * Created by rafalwesolowski on 13/07/2016.
 */
public class GameData {

    @SerializedName("name")
    private String mName;

    @SerializedName("jackpot")
    private String mJackpot;

    @SerializedName("date")
    private String mDate;

    public String getName() {
        return mName;
    }

    public String getJackpot() {
        return mJackpot;
    }

    public String getDate() {
        return mDate;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setJackpot(String jackpot) {
        mJackpot = jackpot;
    }

    public void setDate(String date) {
        mDate = date;
    }

    /**
     * Returns Jackpot value for View.
     *
     * @param context  Context
     * @param currency Currency code as String
     * @return String jackpot
     */
    public String getJackpotForView(Context context, String currency) {
        return LocaleUtil.getJackpotAmount(context, currency, mJackpot);
    }

    /**
     * Returns Date String for View.
     *
     * @return String date
     */
    public String getDateForView() {
        return DateUtil.getJackpotDate(mDate);
    }
}
