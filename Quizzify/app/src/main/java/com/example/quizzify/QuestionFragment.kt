package com.example.quizzify

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.quizzify.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment() {

    var binding:FragmentQuestionBinding?=null

    private val quizViewModel: MyViewModel by viewModels()
    private lateinit var answeredQuestions: BooleanArray
    private var count=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentQuestionBinding.inflate(inflater,container,false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        answeredQuestions = BooleanArray(quizViewModel.questionBank.size) { false }

        binding?.tvQuestion?.setText(quizViewModel.currentQuestionText)

        binding?.btnTrue?.setOnClickListener(){
            checkAnswer(true)
        }
        binding?.btnFalse?.setOnClickListener(){
            checkAnswer(false)
        }
        binding?.btnNext?.setOnClickListener {
            if(quizViewModel.currentIndex==quizViewModel.questionBank.size-1)
                Toast.makeText(requireContext(), "last question", Toast.LENGTH_SHORT).show()
            else
            {
                quizViewModel.moveToNext()
                updateQs()
            }
        }
        binding?.btnPrev?.setOnClickListener {
            if(quizViewModel.currentIndex==0)
                Toast.makeText(requireContext(), "first question", Toast.LENGTH_SHORT).show()
            else
            {
                quizViewModel.moveToPrev()
                updateQs()
            }
        }

        binding?.btnResult?.setOnClickListener(){
            val percentage =(count.toFloat()/quizViewModel.questionBank.size)*100
            val percent=String.format("%.2f", percentage)
            Toast.makeText(requireContext(), "U scored $percent%", Toast.LENGTH_SHORT).show()
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
        binding?.btnFalse?.isEnabled=false
        binding?.btnTrue?.isEnabled=false

    }

}