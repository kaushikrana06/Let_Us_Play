package com.example.quizapp


import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*


class QuizQuestionsActivity:AppCompatActivity(),View.OnClickListener {

    private var mCurrentPosition: Int = 1 // Default and the first question position
    private var mQuestionsList: ArrayList<Question>? = null

    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0

    // TODO (STEP 3: Create a variable for getting the name from intent.)
    // START
    private var mUserName: String? = null
    // END

    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class


        setContentView(R.layout.activity_quiz_question)

        // TODO (STEP 4: Get the NAME from intent and assign it the variable.)
        // START
        mUserName = intent.getStringExtra(Constants.USER_NAME)
        // END

        mQuestionsList = Constants.getQuestions()

        setQuestion()

        op1.setOnClickListener(this)
        op2.setOnClickListener(this)
        op3.setOnClickListener(this)
        op4.setOnClickListener(this)
        submit.setOnClickListener(this)
    }

     override fun onClick(v: View?) {

        when (v?.id) {

            R.id.op1 -> {

                selectedOptionView(op1, 1)
            }

            R.id.op2 -> {

                selectedOptionView(op2, 2)
            }

            R.id.op3 -> {

                selectedOptionView(op3, 3)
            }

            R.id.op4 -> {

                selectedOptionView(op4, 4)
            }

            R.id.submit -> {

                if (mSelectedOptionPosition == 0) {

                    mCurrentPosition++

                    when {

                        mCurrentPosition <= mQuestionsList!!.size -> {

                            setQuestion()
                        }
                        else -> {

                            // TODO (STEP 5: Now remove the toast message and launch the result screen which we have created and also pass the user name and score details to it.)
                            // START
                            val intent =
                                Intent(this@QuizQuestionsActivity, Result::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList!!.size)
                            startActivity(intent)
                            finish()
                            // END
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)

                    // This is to check if the answer is wrong
                    if (question!!.correct != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong)
                    }
                    else {
                        mCorrectAnswers++
                    }

                    // This is for correct answer
                    answerView(question.correct, R.drawable.correct)

                    if (mCurrentPosition == mQuestionsList!!.size) {
                        submit.text = "FINISH"
                    } else {
                        submit.text = "GO TO NEXT QUESTION"
                    }

                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    /**
     * A function for setting the question to UI components.
     */
    private fun setQuestion() {

        val question =
            mQuestionsList!![mCurrentPosition - 1] // Getting the question from the list with the help of current position.

        defaultOptionsView()

        if (mCurrentPosition == mQuestionsList!!.size) {
            submit.text = "FINISH"
        } else {
            submit.text = "SUBMIT"
        }

        bar.progress = mCurrentPosition
        score.text = "$mCurrentPosition" + "/" + bar.max

        Ques.text = question.question
        img.setImageResource(question.image)
        op1.text = question.option1
        op2.text = question.option2
        op3.text = question.option3
        op4.text = question.option4
    }

    /**
     * A function to set the view of selected option view.
     */
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {

        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(
            Color.parseColor("#FF3700B3")
        )
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this,
            R.drawable._new
        )
    }

    /**
     * A function to set default options view when the new question is loaded or when the answer is reselected.
     */
    private fun defaultOptionsView() {

        val options = ArrayList<TextView>()
        options.add(0, op1)
        options.add(1, op2)
        options.add(2, op3)
        options.add(3, op4)

        for (option in options) {
            option.setTextColor(Color.parseColor("#FF3700B3"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.shape_size
            )
        }
    }

    /**
     * A function for answer view which is used to highlight the answer is wrong or right.
     */
    private fun answerView(answer: Int, drawableView: Int) {

        when (answer) {

            1 -> {
                op1.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            2 -> {
                op2.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            3 -> {
                op3.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
            4 -> {
                op4.background = ContextCompat.getDrawable(
                    this,
                    drawableView
                )
            }
        }
    }
}