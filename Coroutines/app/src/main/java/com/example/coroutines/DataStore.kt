package com.example.coroutines

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceDataStore
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope

import com.example.coroutines.databinding.ActivityDataStoreBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class DataStore : AppCompatActivity() {
    private lateinit var binding: ActivityDataStoreBinding

    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            lifecycleScope.launch{
                save(
                    binding.etkey.text.toString(),
                    binding.etvalue.text.toString()
                )
            }
        }
        binding.btnGet.setOnClickListener {
            lifecycleScope.launch {
                val result = read(
                    binding.etget.text.toString()
                )
                binding.tvtoast.text = result?.toString()?: "No value found for the given key."
            }
        }

    }
    private suspend fun save(key: String, value: String) {
        val dataStoreKey = intPreferencesKey(key)
        try {
            applicationContext.dataStore.edit{ settings ->
                settings[dataStoreKey] = value.toInt()
            }
            Toast.makeText(applicationContext, "Record saved into dataStore", Toast.LENGTH_LONG).show()
        } catch (_: Exception) {
            Toast.makeText(
                applicationContext,
                "Failed to save, have a look into the value you sent.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    private suspend fun read(key: String): Int?{
        val dataStoreKey = intPreferencesKey(key)
        val pref = applicationContext.dataStore.data.first()
        return pref[dataStoreKey]
    }


}

