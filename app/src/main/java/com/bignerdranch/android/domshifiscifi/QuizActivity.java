package com.bignerdranch.android.domshifiscifi;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends ActionBarActivity {
    private int questionTotal=1;
    private static final String TAG="QuizActivity";
    private int userScore=0;
    private Boolean UserAnswer;
    //private Button mPrevButton;
    private Button mTrueButton;
    private Button mFalseButton;
    //private Button mNextButton;
    private TextView mQuestionTextView;
    private TextView mUserScore;
    private MediaPlayer correctSound;
    private MediaPlayer incorrectSound;
    private int mCurrentIndex = 0;
    private ImageView sciencePicture;
    private static final String KEY_INDEX="index";
    private static final String USERSCORE_INDEX="userscore";

    private TrueFalse[] mQuestionBank = new TrueFalse[] {
            new TrueFalse("Electrons are larger than molecules.",false),
            new TrueFalse("The Atlantic Ocean is the biggest ocean on Earth.",false),
            new TrueFalse("The chemical make up food often changes when you cook it.",true),
            new TrueFalse("Sharks are mammals.",false),
            new TrueFalse("The human body has four lungs.",false),
            new TrueFalse("Atoms are most stable when their outer shells are full.",true),
            new TrueFalse("Filtration separates mixtures based upon their particle size",true),
            new TrueFalse("Venus is the closest planet to the Sun.",false),
            new TrueFalse("Conductors have low resistance.",true),
            new TrueFalse("Molecules can have atoms from more than one chemical element.",true),
            new TrueFalse("Water is an example of a chemical element.",false),
            new TrueFalse("The study of plants is known as botany.",true),
            new TrueFalse("Mount Kilimanjaro is the tallest mountain in the world.",false),
            new TrueFalse("Floatation separates mixtures based on density",true),
            new TrueFalse("Herbivores eat meat.",false),
            new TrueFalse("Atomic bombs work by atomic fission.",true),
            new TrueFalse("Molecules are chemically bonded.",true),
            new TrueFalse("Spiders have six legs.",false),
            new TrueFalse("Kelvin is a measure of temperature.",true),
            new TrueFalse("The human skeleton is made up of less than 100 bones.",false)

    };




    private void increaseQuestionCount(){
        questionTotal+=1;
    }

    //updates picture that represents science knowledge
    private void drawSciencePicture()
    {
        if (userScore<7) sciencePicture.setImageResource(R.drawable.stimpy);
        else if (7<=userScore && userScore<13) sciencePicture.setImageResource(R.drawable.george);
        else sciencePicture.setImageResource(R.drawable.einstein);

    }

    //helper function to cycle through next question
    private void updateQuestionNext(){

        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        String question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question); }


    //handles logic for comparing user response to the actual answer
    private Boolean IsAnswerCorrect(Boolean UserAnswer){
        Boolean CorrectAnswer=mQuestionBank[mCurrentIndex].isTrueQuestion();
        return UserAnswer == CorrectAnswer;
    }
    //handles logic for updating player score in the event  their response is correct
    private void correctAnswer(){
        userScore+=1;
        mUserScore.setText(Integer.toString(userScore));
        Toast.makeText(QuizActivity.this,R.string.correct_response,Toast.LENGTH_SHORT).show();
        correctSound = MediaPlayer.create(this,R.raw.correct);
        correctSound.start();
        drawSciencePicture();
        updateQuestionNext();
    }
    //handles logic in case user answer is incorrect
    private void wrongAnswer()
    {
        Toast.makeText(QuizActivity.this,R.string.incorrect_response,Toast.LENGTH_SHORT).show();
        incorrectSound=MediaPlayer.create(this,R.raw.incorrect);
        incorrectSound.start();
        drawSciencePicture();
        updateQuestionNext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"OnCreate Called");
        setContentView(R.layout.activity_quiz);
        sciencePicture=(ImageView)findViewById(R.id.sciencePicture);
        drawSciencePicture();

        //Next line allows for saving of important variables
        //So that in the event the screen orientation changes
        //The app will "remember" what they were when a new onCreate is called
        // savedInstanceState is of type Bundle
        //Which is essentially a hash table, which allows for easy lookup
        //The second parameter in the call represents what the default value should be in case of a bug
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            userScore=savedInstanceState.getInt(USERSCORE_INDEX,0);
        }

        mUserScore=(TextView)findViewById(R.id.playerScore);
        mUserScore.setText(Integer.toString(userScore));
        mQuestionTextView=(TextView)findViewById(R.id.question_text_view);
        String question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);




        //user answers question with True
        mTrueButton=(Button)findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(IsAnswerCorrect(true))
                    correctAnswer();
                else wrongAnswer();
                increaseQuestionCount();

            }
        });


        mFalseButton=(Button)findViewById(R.id.false_button);
        //user answers question with False
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsAnswerCorrect(false))
                {

                    correctAnswer();
                }
                else wrongAnswer();
                increaseQuestionCount();



            }
        });





    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);
        savedInstanceState.putInt(USERSCORE_INDEX,userScore);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quiz, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
