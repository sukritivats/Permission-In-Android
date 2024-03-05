package com.example.quizzify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.quizzify.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment() {

    var binding:FragmentQuestionBinding?=null

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true))
    private var currentIndex = 0
    private val answeredQuestions = BooleanArray(questionBank.size) { false }
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

        binding?.btnTrue?.setOnClickListener(){
            checkAnswer(true)
        }
        binding?.btnFalse?.setOnClickListener(){
            checkAnswer(false)
        }
        binding?.btnNext?.setOnClickListener {
            if(currentIndex==questionBank.size-1)
            {
                Toast.makeText(requireContext(),"This is last question", Toast.LENGTH_SHORT).show()
            }
            else{
                currentIndex = (currentIndex+ 1) % questionBank.size
                updateQs()
            }

        }
        binding?.btnPrev?.setOnClickListener {
            if(currentIndex==0)
            {
                Toast.makeText(requireContext(),"This is first question", Toast.LENGTH_SHORT).show()
            }
            else{
                currentIndex = (currentIndex - 1) % questionBank.size
                updateQs()
            }

        }

        binding?.btnResult?.setOnClickListener(){
            val percentage =(count.toFloat()/questionBank.size)*100
            val percent=String.format("%.2f", percentage)
            Toast.makeText(requireContext(), "U scored $percent%", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateQs() {

        val questionTextResId = questionBank[currentIndex].textResId
        binding?.tvQuestion?.setText(questionTextResId)

        if (answeredQuestions[currentIndex]) {
            binding?.btnFalse?.isEnabled=false
            binding?.btnTrue?.isEnabled=false
        } else {
            binding?.btnFalse?.isEnabled=true
            binding?.btnTrue?.isEnabled=true
        }
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
            count++
        } else {
            R.string.incorrect_toast
        }
        answeredQuestions[currentIndex] = true
        binding?.btnFalse?.isEnabled=false
        binding?.btnTrue?.isEnabled=false

    }

}