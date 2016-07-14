package rw.igttestapp.util;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import rw.igttestapp.database.SQLiteHelper;
import rw.igttestapp.datamodel.GameData;
import rw.igttestapp.datamodel.GameResponse;

/**
 * Created by rafalwesolowski on 14/07/2016.
 */
public class SQLiteUtil {

    private static final String KEY_SUCCESS = "success";

    /**
     * Fills the data from Cursor object into GameResponse object
     *
     * @param cursor Cursor data fetched from database
     * @param gameResponse GameResponse object to be filled in
     */
    public static void fillGameData(Cursor cursor, GameResponse gameResponse) {

        // Mock server response success as data saved to database is always successful
        gameResponse.setResponse(KEY_SUCCESS);
        // Assign currency only once
        gameResponse.setCurrency(cursor.getString(SQLiteHelper.KEY_CURRENCY_INDEX));

        List<GameData> gameData = new ArrayList<>();
        do {
            GameData singleGameData = new GameData();

            // Skip the first and second column (0 and 1 indexes) as it's currency which has been already assigned
            singleGameData.setName(cursor.getString(SQLiteHelper.KEY_NAME_INDEX));
            singleGameData.setJackpot(cursor.getString(SQLiteHelper.KEY_JACKPOT_INDEX));
            singleGameData.setDate(cursor.getString(SQLiteHelper.KEY_DATE_INDEX));

            gameData.add(singleGameData);
        }
        while (cursor.moveToNext());

        gameResponse.setData(gameData);
    }
}
