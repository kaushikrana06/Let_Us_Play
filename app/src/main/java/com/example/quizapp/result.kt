package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_result.*


class Result : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // TODO (STEP 6: Hide the status bar and get the details from intent and set it to the UI. And also add a click event to the finish button.)
        // START
        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val userName = intent.getStringExtra(Constants.USER_NAME)
        name.text = userName


        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        result.text = "Your Score is $correctAnswers out of $totalQuestions."

        if(correctAnswers == totalQuestions)
        {
            trophy.setImageResource(R.drawable.tro)
            congo.text="Well Played !!!"
        }else{
            trophy.setImageResource(R.drawable.tro1)
            congo.text="Try Again "
        }

        finish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        // END
    }
}