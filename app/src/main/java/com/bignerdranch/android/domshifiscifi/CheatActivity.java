package com.bignerdranch.android.domshifiscifi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by dom on 5/17/2015.
 */
public class CheatActivity extends ActionBarActivity {
    private boolean mAnswerIsTrue;
    private Button showAnswerButton;
    private TextView answerTextView;
    private static final String QUESTION_ANSWER="QUESTION_ANSWER";
    public static final String EXTRA_ANSWER_SHOWN ="EXTRA_ANSWER_SHOWN";


    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    private void displayCorrectAnswer(){
        if (mAnswerIsTrue){
            answerTextView.setText("True");

        else
            answerTextView.setText("False");


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);


        answerTextView=(TextView)findViewById(R.id.answerTextView);
        mAnswerIsTrue=getIntent().getBooleanExtra(QUESTION_ANSWER,false);
        showAnswerButton=(Button)findViewById(R.id.showAnswerButton);
        setAnswerShownResult(false);


        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnswerShownResult(true);
            }
                displayCorrectAnswer();
            }
        });
    }
}



