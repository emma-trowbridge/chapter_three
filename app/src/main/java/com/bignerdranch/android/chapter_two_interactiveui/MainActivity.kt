package com.bignerdranch.android.chapter_two_interactiveui

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bignerdranch.android.chapter_two_interactiveui.databinding.ActivityMainBinding

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
