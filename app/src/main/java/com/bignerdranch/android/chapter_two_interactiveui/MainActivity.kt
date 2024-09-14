package com.bignerdranch.android.chapter_two_interactiveui

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bignerdranch.android.chapter_two_interactiveui.databinding.ActivityMainBinding

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

        binding.nextButton.setOnClickListener{
            currentIndex = (currentIndex + 1) % questionBank.size
            //val questionTextResId = questionBank[currentIndex].textResId
            //binding.questionTextView.setText(questionTextResId)
            updateQuestion()
        }

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

        }

    }
