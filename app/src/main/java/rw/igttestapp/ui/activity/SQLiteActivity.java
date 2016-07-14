package rw.igttestapp.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import rw.igttestapp.database.GameDataSource;

/**
 * Created by rafalwesolowski on 14/07/2016.
 */
public class SQLiteActivity extends AppCompatActivity {

    private GameDataSource mGameDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGameDataSource = new GameDataSource(this);
        mGameDataSource.open();
    }

    @Override
    protected void onResume() {
        mGameDataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mGameDataSource.close();
        super.onPause();
    }

    public GameDataSource getGameDataSource() {
        return mGameDataSource;
    }
}
