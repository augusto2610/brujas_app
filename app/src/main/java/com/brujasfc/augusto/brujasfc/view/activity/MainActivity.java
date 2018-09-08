package com.brujasfc.augusto.brujasfc.view.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.brujasfc.augusto.brujasfc.R;
import com.brujasfc.augusto.brujasfc.model.Match;
import com.brujasfc.augusto.brujasfc.util.AppExecutors;
import com.brujasfc.augusto.brujasfc.view.adapter.FixtureAdapter;
import com.brujasfc.augusto.brujasfc.viewModel.MainViewModel;

import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private FixtureAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        mRecyclerView = findViewById(R.id.recyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setHasFixedSize(true);

        DividerItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), VERTICAL);
        mRecyclerView.addItemDecoration(decoration);

        mAdapter = new FixtureAdapter(getApplicationContext(), new FixtureAdapter.ItemClickListener() {
            @Override
            public void onItemClickListener(int matchId) {
                //TODO New activity in order to see the information about the match.
            }
        });

        mRecyclerView.setAdapter(mAdapter);

        initModelView();
    }

    private void initModelView() {
        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getMatchList().observe(this, new Observer<List<Match>>() {
            @Override
            public void onChanged(@Nullable List<Match> matches) {
                Log.d(TAG, "Updating matches list - Size: " + matches.size());
                mAdapter.setMatches(matches);
            }
        });
    }
}
