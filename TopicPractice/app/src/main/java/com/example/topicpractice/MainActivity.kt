package com.example.topicpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.constraintlayout.helper.widget.MotionEffect.TAG
import com.example.topicpractice.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private var binding:ActivityMainBinding?=null

    private val quizViewModel: MyViewModel by viewModels()
    private lateinit var answeredQuestions: BooleanArray
    private var count=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        answeredQuestions = BooleanArray(quizViewModel.questionBank.size) { false }

        clickHandler()
        binding?.tvQuestion?.setText(quizViewModel.currentQuestionText)
    }

    private fun clickHandler() {


        binding?.btnTrue?.setOnClickListener(){
            checkAnswer(true)
        }
        binding?.btnFalse?.setOnClickListener(){
            checkAnswer(false)
        }
        binding?.btnNext?.setOnClickListener {
           if(quizViewModel.currentIndex==quizViewModel.questionBank.size-1)
               Toast.makeText(this, "last question", Toast.LENGTH_SHORT).show()
            else
           {
               quizViewModel.moveToNext()
               updateQs()
           }
        }
        binding?.btnPrev?.setOnClickListener {
            if(quizViewModel.currentIndex==0)
                Toast.makeText(this, "first question", Toast.LENGTH_SHORT).show()
            else
            {
                quizViewModel.moveToPrev()
                updateQs()
            }
        }
        binding?.btnResult?.setOnClickListener(){
            val percentage =(count.toFloat()/quizViewModel.questionBank.size)*100
            val percent=String.format("%.2f", percentage)
            Toast.makeText(this, "U scored $percent%", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateQs() {
        val question = quizViewModel.currentQuestionText
        binding?.tvQuestion?.setText(question)

        if (answeredQuestions[quizViewModel.currentIndex]) {
            binding?.btnFalse?.isEnabled=false
            binding?.btnTrue?.isEnabled=false
        } else{
            binding?.btnFalse?.isEnabled=true
            binding?.btnTrue?.isEnabled=true
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val answer=quizViewModel.currentQuestionAnswer
        if(userAnswer==answer)
            count++

        answeredQuestions[quizViewModel.currentIndex] = true
        binding?.btnTrue?.isEnabled=false
        binding?.btnFalse?.isEnabled=false

    }

}

