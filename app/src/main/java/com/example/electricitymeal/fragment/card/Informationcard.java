package com.example.electricitymeal.fragment.card;

public class Informationcard {
    public int mResult;
    public int mPower;
    public int mCount;
    public int mHours;
    public String mGoal;
    public int mMinutes;

    public Informationcard(int mCount, int mHours, int mMinutes, int mPower) {
        this.mPower = mPower;
        this.mCount = mCount;
        this.mHours = mHours;
        this.mMinutes = mMinutes;
        this.mGoal = "";
        this.mResult = (int) Math.ceil(mPower * mCount * (mHours + ((mMinutes * 60) / 100)));
    }
}
