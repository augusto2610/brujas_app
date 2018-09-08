package com.brujasfc.augusto.brujasfc.model;

import com.google.firebase.firestore.PropertyName;

public class MatchScore {

    @PropertyName("score")
    public String mScore;

    @PropertyName("result")
    public String mResult;

    public MatchScore(){

    }

    public MatchScore(String mScore, String mResult) {
        this.mScore = mScore;
        this.mResult = mResult;
    }

}
