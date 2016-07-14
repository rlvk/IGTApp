package rw.igttestapp.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import rw.igttestapp.R;
import rw.igttestapp.api.ApiClient;
import rw.igttestapp.database.GameDataSource;
import rw.igttestapp.datamodel.GameResponse;
import rw.igttestapp.ui.adapter.GameAdapter;

public class MainActivity extends SQLiteActivity implements Response.Listener<GameResponse>, Response.ErrorListener,
        GameAdapter.ChooseGameListener {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        GameDataSource gameDataSource = getGameDataSource();
        GameResponse gameResponse = gameDataSource.getGameData(true);
        if (gameResponse != null) {
            setupAdapterWithData(gameResponse);
        } else {
            ApiClient.getInstance(this).getGames(this, this);
        }
    }

    @Override
    protected void onDestroy() {
        ApiClient.getInstance(this).cancelAllRequests();
        super.onDestroy();
    }

    @Override
    public void onResponse(GameResponse response) {
        mProgressBar.setVisibility(View.GONE);

        GameDataSource gameDataSource = getGameDataSource();
        gameDataSource.saveGameData(response, true);

        setupAdapterWithData(response);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        mProgressBar.setVisibility(View.GONE);
        Toast.makeText(this, R.string.error_generic, Toast.LENGTH_SHORT).show();
    }

    private void setupAdapterWithData(GameResponse response) {
        if (response != null) {
            GameAdapter adapter = new GameAdapter(response.getData(), this);
            mRecyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onChooseGame(int position) {
        Intent intent = new Intent(this, GameActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(GameActivity.KEY_POSITION, position);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
