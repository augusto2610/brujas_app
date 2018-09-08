package com.brujasfc.augusto.brujasfc.model;

import com.google.firebase.firestore.PropertyName;

import java.util.Date;

/**
 * Created by augustopinto on 8/16/18.
 */
public class Match {

    private int id;

    @PropertyName("match_number")
    public int mMatchNumber;

    @PropertyName("field_number")
    public int mFieldNumber;

    @PropertyName("opponent_name")
    public String mOpponent;

    @PropertyName("date")
    public Date mMatchDate;

    @PropertyName("match_score")
    public MatchScore mMatchScore;

    public Match() {

    }

    public Match(int id, int matchNumber, int fieldNumber, String opponent) {
        this.id = id;
        this.mMatchNumber = matchNumber;
        this.mFieldNumber = fieldNumber;
        this.mOpponent = opponent;
    }

    public Match(int matchNumber, int fieldNumber, String opponent) {
        this.mMatchNumber = matchNumber;
        this.mFieldNumber = fieldNumber;
        this.mOpponent = opponent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatchNumber() {
        return mMatchNumber;
    }

    public void setMatchNumber(int matchNumber) {
        this.mMatchNumber = matchNumber;
    }

    public int getFieldNumber() {
        return mFieldNumber;
    }

    public void setFieldNumber(int fieldNumber) {
        this.mFieldNumber = fieldNumber;
    }

    public String getOpponent() {
        return mOpponent;
    }

    public void setOpponent(String mOpponent) {
        this.mOpponent = mOpponent;
    }

    public Date getMatchDate() {
        return mMatchDate;
    }

    public void setMatchDate(Date mMatchDate) {
        this.mMatchDate = mMatchDate;
    }

}
