package com.example.colorpicker

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.example.colorpicker.databinding.FragmentEasyBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FragmentEasy : Fragment() {

    private var binding:FragmentEasyBinding?=null
    private val colors = mutableListOf(Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE)
    private var score: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentEasyBinding.inflate(inflater, container, false)
         return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.Main).launch {
            smallBoxColor()
            gridColor()
//            setupButtonClickListeners()
        }
    }

    // color of small box
    private fun smallBoxColor(): Int {
        var smallBoxColor = Color.WHITE

        CoroutineScope(Dispatchers.Main).launch  {
            while (true) {
                val color = getRandomColorForSmallBox()
                binding?.SmallBox?.setBackgroundColor(color)
                smallBoxColor = color
                delay(2000)
            }
        }
        return smallBoxColor
    }
    private fun getRandomColorForSmallBox():Int
    {
        val colors = listOf(Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE)
        return colors.random()
    }

    // color of 4 boxes
    private fun gridColor() {

        CoroutineScope(Dispatchers.Main).launch {
            while (true) {
                binding?.btnBlue?.setBackgroundColor(getRandomColor())
                binding?.btnGreen?.setBackgroundColor(getRandomColor())
                binding?.btnYellow?.setBackgroundColor(getRandomColor())
                binding?.btnRed?.setBackgroundColor(getRandomColor())
                delay(2000)
            }
        }
    }
    private fun getRandomColor(): Int {
        if (colors.isEmpty()) {
            colors.addAll(listOf(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW))
        }
        val randomIndex = (0 until colors.size).random()
        return colors.removeAt(randomIndex)
    }

    // click listener
//
//    private fun setupButtonClickListeners() {
//        binding?.apply {
//            btnBlue.setOnClickListener { checkColor(it) }
//            btnGreen.setOnClickListener { checkColor(it) }
//            btnYellow.setOnClickListener { checkColor(it) }
//            btnRed.setOnClickListener { checkColor(it) }
//        }
//    }
//
//    private fun checkColor(view: View) {
//        val buttonColor = (view.background as? ColorDrawable)?.color ?: Color.WHITE
//        val smallBoxColor = (binding?.SmallBox?.background as? ColorDrawable)?.color ?: Color.WHITE
//
//        if (buttonColor != smallBoxColor) {
//            showGameOverDialog()
//            lifecycleScope.launch{
//                save(
//                    score
//                )
//            }
//        } else {
//            score++
//            updateScoreTextView()
//        }
//    }
//    private fun updateScoreTextView() {
//        val scoreText = "SCORE: $score"
//        binding?.tvScore?.text = scoreText
//    }
//
//    private fun showGameOverDialog() {
//        AlertDialog.Builder(requireContext())
//            .setTitle("Game Over!")
//            .setMessage("You clicked on the wrong color. Try again.")
//            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
//            .show()
//    }
//
//    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "score_prefs")
//    private val SCORE_KEY = intPreferencesKey("score_key")
//
//    private suspend fun save(score: Int) {
//        requireContext().dataStore.edit { preferences ->
//            preferences[SCORE_KEY] = score
//        }
//    }
//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding = null
//    }
}