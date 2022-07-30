package com.dahdotech.trucitizen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.dahdotech.trucitizen.databinding.ActivityMainBinding;
import com.dahdotech.trucitizen.model.Question;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int currentQuestionIndex = 0;
    private Question[] questionBank = new Question[]{
            new Question(R.string.question_amendments, false),
            new Question(R.string.question_constitution, true),
            new Question(R.string.question_declaration, true),
            new Question(R.string.question_independence_rights, true),
            new Question(R.string.question_religion, true),
            new Question(R.string.question_government, false),
            new Question(R.string.question_government_feds, false),
            new Question(R.string.question_government_senators, true)
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //binding.questionTextView.setText(questionBank[0].getAnswerResId());
        binding.trueButton.setOnClickListener(view -> {
            checkAnswer(true);
        });

        binding.falseButton.setOnClickListener(view -> {
            checkAnswer(false);
        });

        binding.nextButton.setOnClickListener(view ->{
            //Log.d("Main", "onCreate" + questionBank[currentQuestionIndex++].getAnswerResId());
            currentQuestionIndex = (currentQuestionIndex + 1) % questionBank.length;
            updateQuestion();
        });
        binding.prevButton.setOnClickListener(view -> {
            if(currentQuestionIndex > 0){
                currentQuestionIndex = (currentQuestionIndex - 1) % questionBank.length;
                updateQuestion();
            }
        });
    }
    private void checkAnswer(boolean userChoice){
        boolean realAnswer = questionBank[currentQuestionIndex].isAnswerTrue();
        int messageId;
        if(realAnswer == userChoice)
            messageId = R.string.correct_answer;
        else
            messageId = R.string.wrong_answer;

        Snackbar.make(binding.imageView, messageId, Snackbar.LENGTH_LONG).show();
    }
    private void updateQuestion() {
        binding.questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());
    }
}