package com.brujasfc.augusto.brujasfc.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brujasfc.augusto.brujasfc.R;
import com.brujasfc.augusto.brujasfc.model.Match;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by augustopinto on 8/22/18.
 */

public class FixtureAdapter extends RecyclerView.Adapter<FixtureAdapter.ViewHolder> {

    private static final String TAG = FixtureAdapter.class.getSimpleName();

    private List<Match> mMatchesList;
    private ItemClickListener mItemClickListener;
    private Context mContext;

    public interface ItemClickListener {
        void onItemClickListener(int matchId);
    }

    public FixtureAdapter(Context context, ItemClickListener listener) {
        mContext = context;
        mItemClickListener = listener;
        mMatchesList = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fixture_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Match match = mMatchesList.get(i);

        SimpleDateFormat format = new SimpleDateFormat("EE dd-MMM HH:mm");
        viewHolder.mFieldNumberTextView.setText(
                mContext.getString(R.string.match_field_number, format.format(match.mMatchDate),
                        String.valueOf(match.getFieldNumber())));
        viewHolder.mMatchOpponentTextView.setText(
                mContext.getString(R.string.opponent_text_item, match.getOpponent()));
        viewHolder.mMatchNumberTextView.setText(String.valueOf(match.getMatchNumber()));

        if (match.mMatchScore != null) {
            int scoreColor = mContext.getResources().getColor(R.color.scoreDrawColor);
            switch (match.mMatchScore.mResult.toUpperCase()) {
                case "L":
                    scoreColor = mContext.getResources().getColor(R.color.scoreLoseColor);
                    break;
                case "W":
                    scoreColor = mContext.getResources().getColor(R.color.scoreWinColor);
                    break;
                case "D":
                    scoreColor = mContext.getResources().getColor(R.color.scoreDrawColor);
                    break;
            }
            viewHolder.mScoreTextView.setText(match.mMatchScore.mScore);
            viewHolder.mScoreTextView.setTextColor(scoreColor);
        }
    }

    @Override
    public int getItemCount() {
        return mMatchesList.size();
    }

    public void setMatches(List<Match> matches) {
        mMatchesList = matches;
        notifyDataSetChanged();
    }

    public List<Match> getMatchesList() {
        return mMatchesList;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mMatchNumberTextView;
        TextView mMatchOpponentTextView;
        TextView mFieldNumberTextView;
        TextView mScoreTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mMatchNumberTextView = itemView.findViewById(R.id.match_number_tv);
            mMatchOpponentTextView = itemView.findViewById(R.id.opponent_match_tv);
            mFieldNumberTextView = itemView.findViewById(R.id.field_number_tv);
            mScoreTextView = itemView.findViewById(R.id.match_score_tv);
        }

        @Override
        public void onClick(View view) {
            //TODO Do nothing for now
        }
    }

}
