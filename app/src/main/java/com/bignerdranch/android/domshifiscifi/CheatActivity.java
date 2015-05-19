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
    private String mQuestionText;
    public static final String QUESTION_ANSWER="QUESTION_ANSWER";
    public static final String EXTRA_ANSWER_SHOWN ="EXTRA_ANSWER_SHOWN";
    private TextView mQuestionTextView;
    private TextView mQuestionAnswerTextView;


    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    //function that gets executed once show answer is pressed
    //sets the appropriate text view's text
    private void displayCorrectAnswer() {
        if (mAnswerIsTrue) {
            mQuestionAnswerTextView.setText("True");
        } else {
            mQuestionAnswerTextView.setText("False");
             }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        //init the two TextViews
        mQuestionTextView=(TextView)findViewById(R.id.mQuestionTextView);
        mQuestionAnswerTextView=(TextView)findViewById(R.id.mQuestionAnswerTextView);


        mAnswerIsTrue=getIntent().getBooleanExtra(QUESTION_ANSWER,false);//stores the answer to the question
        mQuestionText=getIntent().getStringExtra(QuizActivity.QUESTION_TEXT);//stores the question text
        mQuestionTextView.setText(mQuestionText);
        showAnswerButton=(Button)findViewById(R.id.showAnswerButton);


        //represents the default "state" for the intent's extra regarding was the answer shown or not
        //aka up until this point the answer has not been shown
        setAnswerShownResult(false);


        showAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnswerShownResult(true); //once the button is clicked remember that it was

                displayCorrectAnswer();
            }
        });
    }
}



