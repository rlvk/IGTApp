package rw.igttestapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rafalwesolowski on 13/07/2016.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "GamesDatabase";

    // Table name
    public static final String TABLE_NAME = "games";

    // Common Table Columns names

    // Game Content Table Column names
    public static final String KEY_ID = "id";
    public static final String KEY_CURRENCY = "currency";
    public static final String KEY_NAME = "name";
    public static final String KEY_JACKPOT = "jackpot";
    public static final String KEY_DATE = "date";
    public static final String KEY_CREATED_AT = "created_at";

    // Column indexes
    public static final int KEY_ID_INDEX = 0;
    public static final int KEY_CURRENCY_INDEX = 1;
    public static final int KEY_NAME_INDEX = 2;
    public static final int KEY_JACKPOT_INDEX = 3;
    public static final int KEY_DATE_INDEX = 4;
    public static final int KEY_CREATED_AT_INDEX = 5;

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " (" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            KEY_CURRENCY + " TEXT, " +
            KEY_NAME + " TEXT, " +
            KEY_JACKPOT + " TEXT, " +
            KEY_DATE + " TEXT, " +
            KEY_CREATED_AT + " TEXT )";

    private static SQLiteHelper _sqliteInstance;

    public static synchronized SQLiteHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (_sqliteInstance == null) {
            _sqliteInstance = new SQLiteHelper(context.getApplicationContext());
        }
        return _sqliteInstance;
    }

    /**
     * Constructor should be private to prevent direct instantiation.
     * make call to static method "getInstance()" instead.
     */
    private SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}

