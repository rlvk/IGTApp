package rw.igttestapp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.List;

import rw.igttestapp.R;
import rw.igttestapp.datamodel.GameData;

/**
 * Created by rafalwesolowski on 13/07/2016.
 */
public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private final List<GameData> mGameData;
    private final WeakReference<ChooseGameListener> mChooseGameListener;

    /**
     * Constructor with {@link Context} of caller
     *
     * @param gameData {@link List<GameData>}
     * @param chooseGameListener {@link ChooseGameListener}
     */
    public GameAdapter(List<GameData> gameData, ChooseGameListener chooseGameListener) {
        mGameData = gameData;
        mChooseGameListener = new WeakReference(chooseGameListener);
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.list_item_game_type_view, viewGroup, false);
        return new GameViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, final int position) {
        final GameData gameData = mGameData.get(position);
        if (gameData != null) {
            holder.mGameNameView.setText(gameData.getName());
        } else {
            holder.mGameNameView.setText("");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mChooseGameListener != null && mChooseGameListener.get() != null) {
                    mChooseGameListener.get().onChooseGame(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mGameData != null ? mGameData.size() : 0;
    }

    /**
     * Game ViewHolder.
     */
    protected class GameViewHolder extends RecyclerView.ViewHolder {

        protected TextView mGameNameView;

        /**
         * Constructor with the View.
         *
         * @param view view object
         */
        public GameViewHolder(View view) {
            super(view);
            mGameNameView = (TextView) view.findViewById(R.id.game_name_text_view);
        }
    }

    /**
     * Click listener for game.
     */
    public interface ChooseGameListener {

        /**
         * Click Listener for Game.
         *
         * @param position index
         */
        void onChooseGame(int position);
    }
}
