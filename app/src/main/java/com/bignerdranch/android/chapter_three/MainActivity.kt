package com.bignerdranch.android.chapter_three

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.android.chapter_three.databinding.ActivityMainBinding

private const val Tag = "MainActivity"

class MainActivity : AppCompatActivity() {

    //private lateinit var trueButton: Button
    //private lateinit var falseButton: Button


    private  val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)

    )//created list of questions...
        //from Question.kt - looks for integer (R.string.question.()), and then boolean (true or false)

    private var answers = arrayOfNulls<Boolean>(questionBank.size) //tracks answer of each question, null = unanswered

    private var currentIndex = 0 //always start with 0, first value of index is "0"
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)

        Log.d(Tag, "onCreate(Bundle) called")

        //trueButton = findViewById(R.id.true_button)
        //falseButton = findViewById(R.id.false_button)

        binding.trueButton.setOnClickListener {
            /*Toast.makeText(
                this,
                R.string.correct,
                Toast.LENGTH_SHORT
            )
                .show() */

            checkAnswer(userAnswer = true)
        }

        binding.falseButton.setOnClickListener {
            /*Toast.makeText(
                this,
                R.string.incorrect,
                Toast.LENGTH_SHORT
            )
                .show()*/

            checkAnswer(userAnswer = false)
            }

        binding.previousButton.setOnClickListener{
            currentIndex = if (currentIndex - 1 < 0) {
                questionBank.size - 1 //wraps around to the last question
            }else {
                (currentIndex - 1) % questionBank.size
            }
            updateQuestion()
        }

        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            //val questionTextResId = questionBank[currentIndex].textResId
            //binding.questionTextView.setText(questionTextResId)
            updateQuestion()
        }

        binding.questionTextView.setOnClickListener{
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        //got rid of enableAnswerButtons() from next, previous, and question buttons

        //var questionTextResId = questionBank[currentIndex].textResId
        //binding.questionTextView.setText(questionTextResId)
        updateQuestion()
        }

        //logs:

        override fun onStart(){
            super.onStart()
            Log.d(Tag, "onStart() called")

        }

        override fun onPause(){
            super.onPause()
            Log.d(Tag, "onPause() called")
        }

        override fun onStop(){
            super.onStop()
            Log.d(Tag, "onStop() called")
        }

        override fun onDestroy(){
            super.onDestroy()
            Log.d(Tag, "onDestroy() called")
        }


        private fun updateQuestion(){
            val questionTextResId = questionBank[currentIndex].textResId
            binding.questionTextView.setText(questionTextResId)

            val answered = answers[currentIndex]
            if (answered != null) {
                disableAnswerButtons() //if answered, disabled buttons
            } else {
                enableAnswerButtons() //else enable the buttons so the user answers the question
            }
        }

        private fun checkAnswer(userAnswer:Boolean){
            val correctAnswer = questionBank[currentIndex].answer //grabbing second quality
            val messageResID = if (userAnswer == correctAnswer){
                R.string.correct
            } else{
                R.string.incorrect
            }

            Toast.makeText(
                this,
                messageResID,
                Toast.LENGTH_SHORT)
                .show()

            answers[currentIndex] = userAnswer == correctAnswer //saves the users answer, boolean = true/false

            disableAnswerButtons() // disables both buttons after user answers
        }

        private fun disableAnswerButtons() {
            binding.trueButton.isEnabled = false
            binding.falseButton.isEnabled = false
        }

        private  fun enableAnswerButtons() {
            binding.trueButton.isEnabled = true
            binding.falseButton.isEnabled = true
        }
    }
