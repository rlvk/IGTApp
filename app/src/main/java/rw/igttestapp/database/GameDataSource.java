package rw.igttestapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import rw.igttestapp.datamodel.GameData;
import rw.igttestapp.datamodel.GameResponse;
import rw.igttestapp.util.DateUtil;
import rw.igttestapp.util.SQLiteUtil;

/**
 * Created by rafalwesolowski on 14/07/2016.
 */
public class GameDataSource {

    // Database fields
    private SQLiteDatabase database;
    private SQLiteHelper dbHelper;
    private final String[] gameColumns = {SQLiteHelper.KEY_ID, SQLiteHelper.KEY_CURRENCY,
        SQLiteHelper.KEY_NAME, SQLiteHelper.KEY_JACKPOT, SQLiteHelper.KEY_DATE,
        SQLiteHelper.KEY_CREATED_AT};

    public GameDataSource(Context context) {
        dbHelper = SQLiteHelper.getInstance(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    /**
     * Save all GameResponse data.
     *
     * @param gameResponse {@link GameResponse}
     */
    public void saveGameData(GameResponse gameResponse, boolean deleteOld){
        if (gameResponse == null) {
            return;
        }

        if (deleteOld) {
            removeAllGameData();
        }

        // Get reference to writable DB
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();

        for(GameData gameData : gameResponse.getData()) {
            values.put(SQLiteHelper.KEY_CURRENCY, gameResponse.getCurrency());
            values.put(SQLiteHelper.KEY_NAME, gameData.getName());
            values.put(SQLiteHelper.KEY_JACKPOT, gameData.getJackpot());
            values.put(SQLiteHelper.KEY_DATE, gameData.getDate());

            values.put(SQLiteHelper.KEY_CREATED_AT, DateUtil.getStringDate(System.currentTimeMillis()));

            // Null as don't insert row when there is no value
            db.insert(SQLiteHelper.TABLE_NAME, null, values);
        }
    }

    /**
     * Removes all game data.
     *
     */
    private void removeAllGameData(){
        // Get reference to writable DB
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(SQLiteHelper.TABLE_NAME, null, null);
    }

    /**
     * Get All Game data.
     *
     * @param shouldCheckExpiration boolean
     * @return GameResponse object
     */
    public GameResponse getGameData(boolean shouldCheckExpiration) {
        GameResponse gameResponse = new GameResponse();
        // 1. build the query
        String query = "SELECT * FROM " + SQLiteHelper.TABLE_NAME;
        // 2. get reference to writable DB
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (!cursor.moveToFirst()) {
            return null;
        }

        String createdAt = cursor.getString(SQLiteHelper.KEY_CREATED_AT_INDEX);
        if (shouldCheckExpiration && DateUtil.isDataExpired(createdAt)) {
            // Data is expired
            return null;
        }

        SQLiteUtil.fillGameData(cursor, gameResponse);

        cursor.close();

        return gameResponse;
    }
}
