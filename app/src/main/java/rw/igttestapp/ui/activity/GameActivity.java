package rw.igttestapp.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import rw.igttestapp.R;
import rw.igttestapp.database.GameDataSource;
import rw.igttestapp.datamodel.GameData;
import rw.igttestapp.datamodel.GameResponse;

/**
 * Created by rafalwesolowski on 13/07/2016.
 */
public class GameActivity extends SQLiteActivity {

    public static final String KEY_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_layout);

        Bundle extras = getIntent().getExtras();
        int position = 0;
        if (extras != null) {
            position = extras.getInt(KEY_POSITION);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView gameName = (TextView) findViewById(R.id.game_name_tv);
        TextView gameJackpot = (TextView) findViewById(R.id.game_jackpot_tv);
        TextView gameDate = (TextView) findViewById(R.id.game_date_tv);

        GameDataSource gameDataSource = getGameDataSource();
        GameResponse gameResponse = gameDataSource.getGameData(false);

        if (gameResponse != null && gameResponse.getData() != null) {
            List<GameData> gameDataList = gameResponse.getData();
            if (gameDataList != null && !gameDataList.isEmpty()) {
                GameData gameData = gameDataList.get(position);

                // Game name
                gameName.setText(gameData.getName());

                // Jackpot
                gameJackpot.setText(gameData.getJackpotForView(this, gameResponse.getCurrency()));

                // Date
                gameDate.setText(gameData.getDateForView());
            }
        }
    }
}
