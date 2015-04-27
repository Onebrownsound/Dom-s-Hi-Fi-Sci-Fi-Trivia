package com.bignerdranch.android.domshifiscifi;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class QuizActivity extends ActionBarActivity {
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



    //updates picture that represents science knowledge
    private void drawSciencePicture()
    {
        if (userScore<5) sciencePicture.setImageResource(R.drawable.stimpy);
        else if (5<=userScore && userScore<13) sciencePicture.setImageResource(R.drawable.stimpy);
        else if (14<=userScore && userScore<19) sciencePicture.setImageResource(R.drawable.george);
        else sciencePicture.setImageResource(R.drawable.einstein);

    }

    //helper function to update questions
    private void updateQuestionNext(){
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        String question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question); }

    //helper function to cycle through questions
    private void updateQuestionPrevious(){
        mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
        String question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);

    }
    //handles logic for comparing user response to the actual answer
    private Boolean IsAnswerCorrect(Boolean UserAnswer){
        Boolean CorrectAnswer=mQuestionBank[mCurrentIndex].isTrueQuestion();
        return UserAnswer == CorrectAnswer;
    }
    //handles logic for updating player score in the event  their response is correct
    private void gainPoints(){
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
        setContentView(R.layout.activity_quiz);
        sciencePicture=(ImageView)findViewById(R.id.sciencePicture);
        drawSciencePicture();

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
                    gainPoints();
                else wrongAnswer();

            }
        });


        mFalseButton=(Button)findViewById(R.id.false_button);
        //user answers question with False
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsAnswerCorrect(false))
                {

                    gainPoints();
                }
                else wrongAnswer();

            }
        });
        //commented out below is the next and previous buttons. No use for them currently.
        /*

        mNextButton=(Button)findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        updateQuestionNext();


        }
        });
        mPrevButton=(Button)findViewById(R.id.prev_button);
        mPrevButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        updateQuestionPrevious();
        }
        });

        */


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
