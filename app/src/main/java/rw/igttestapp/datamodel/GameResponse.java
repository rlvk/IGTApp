package rw.igttestapp.datamodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by rafalwesolowski on 13/07/2016.
 */
public class GameResponse {

    @SerializedName("response")
    private String mResponse;

    @SerializedName("currency")
    private String mCurrency;

    @SerializedName("data")
    private List<GameData> mData;

    public String getResponse() {
        return mResponse;
    }

    public String getCurrency() {
        return mCurrency;
    }

    public List<GameData> getData() {
        return mData;
    }

    public void setResponse(String response) {
        mResponse = response;
    }

    public void setCurrency(String currency) {
        mCurrency = currency;
    }

    public void setData(List<GameData> data) {
        mData = data;
    }
}
