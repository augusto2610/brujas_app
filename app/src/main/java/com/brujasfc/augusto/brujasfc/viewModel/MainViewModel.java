package com.brujasfc.augusto.brujasfc.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.brujasfc.augusto.brujasfc.model.Match;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by augustopinto on 8/21/18.
 */

public class MainViewModel extends AndroidViewModel {

    private static final String TAG = MainViewModel.class.getSimpleName();

    private FirebaseFirestore mDatabase;
    private MutableLiveData<List<Match>> mMatchList = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
        Log.d(TAG, "Retrieving the match list from database");
        mDatabase = FirebaseFirestore.getInstance();
        createMatchList();
    }

    private void createMatchList() {
        mDatabase.collection("matches").orderBy("match_number").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }

                List<Match> matches = new ArrayList<>();
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    Match match = doc.toObject(Match.class);matches.add(match);
                    mMatchList.setValue(matches);
                }

            }
        });
    }

    public LiveData<List<Match>> getMatchList() {
        return mMatchList;
    }
}
