package com.example.colorpicker

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.colorpicker.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private var binding:FragmentHomeBinding?=null
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "score_prefs")
    private val SCORE_KEY = intPreferencesKey("score_key")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.btnEasy?.setOnClickListener(){
            findNavController().navigate(R.id.action_homeFragment_to_fragmentEasy)
        }
        binding?.btnMedium?.setOnClickListener(){
            findNavController().navigate(R.id.action_homeFragment_to_fragmentMedium)
        }
        binding?.btnHard?.setOnClickListener(){
            findNavController().navigate(R.id.action_homeFragment_to_fragmentHard)
        }
        lifecycleScope.launch {
            val score = readScoreFromDataStore(requireContext())
            binding?.etScore?.setText(score.toString())
        }

    }

    private suspend fun readScoreFromDataStore(requireContext: Context): Int {
        val preferences = context?.dataStore?.data?.first()
        return preferences?.get(SCORE_KEY) ?: 0
    }

}