package com.bignerdranch.android.domshifiscifi;

/**
 * Created by dom on 4/20/2015.
 */
public class TrueFalse {

    private String mQuestion;
    private Boolean mTrueQuestion;
    public TrueFalse (String question, boolean trueQuestion){
        mQuestion=question;
        mTrueQuestion=trueQuestion;
    }

    public String getQuestion(){return mQuestion;}
    public void setQuestion(String question){mQuestion=question;}
    public boolean isTrueQuestion(){return mTrueQuestion;}

    public void setTrueQuestion(Boolean trueQuestion) {
        mTrueQuestion = trueQuestion;
    }
}
