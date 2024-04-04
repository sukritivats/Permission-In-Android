package com.example.assignment

import SizeAdapter
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment.Adapters.ColorAdapter
import com.example.assignment.DataClass.ClothingVariant
import com.example.assignment.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var clothingVariants: List<ClothingVariant>
    private var selectedSize: String = ""
    private var maxQuantity: Int = 0
    private var currentQuantity: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        //parsing json data with gson
        val gson = Gson()
        val variantListType = object : TypeToken<List<ClothingVariant>>() {}.type
        val jsonData = readJsonDataFromRawResource(R.raw.clothing_variants)
        val jsonObject = gson.fromJson(jsonData, JsonObject::class.java)
        val clothingVariantsJsonArray = jsonObject.getAsJsonArray("clothing_variants")
        clothingVariants = gson.fromJson(clothingVariantsJsonArray, variantListType)

        val sizes = clothingVariants.map { it.size }.distinct()

        // set the default size
        val defaultSize = sizes.firstOrNull()
        defaultSize?.let {
            selectedSize = it
            updateColorsAndQuantity(it)
        }

        // get size
        val sizeAdapter = SizeAdapter(sizes) { selectedSize ->
            this.selectedSize = selectedSize
            updateColorsAndQuantity(selectedSize)
        }.apply {
            val initialSelectedPosition = 0
            this.setSelectedPosition(initialSelectedPosition)
        }
        binding?.recyclerViewSize?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding?.recyclerViewSize?.adapter = sizeAdapter

        // increment product
        binding?.btnPlus?.setOnClickListener {
            if (currentQuantity < maxQuantity) {
                currentQuantity++
                binding?.tvNumber?.text = currentQuantity.toString()
            }
            updateButtonStates()
            updateTotalPrice()
        }

        //decrement product
        binding?.btnMinus?.setOnClickListener {
            if (currentQuantity > 0) {
                currentQuantity--
                binding?.tvNumber?.text = currentQuantity.toString()
            }
            updateButtonStates()
            updateTotalPrice()
        }

        //toast
        binding?.btnAddToCart?.setOnClickListener {
            val finalAmount = binding?.tvFinalAmountInDollars?.text?.toString()
            Toast.makeText(this, "Final Amount is $finalAmount", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateColorsAndQuantity(selectedSize: String) {
        val colorsForSize = clothingVariants
            .filter { it.size == selectedSize }
            .flatMap { it.available_colors }
            .distinctBy { it.hex_code }

        val colorAdapter = ColorAdapter(colorsForSize)
        binding?.recyclerViewColors?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding?.recyclerViewColors?.adapter = colorAdapter

        maxQuantity = clothingVariants
            .filter { it.size == selectedSize }
            .maxOfOrNull { it.quantity } ?: 0

        currentQuantity = 1
        binding?.tvNumber?.text = currentQuantity.toString()

        updateButtonStates()
        updateTotalPrice()
    }

    @SuppressLint("SetTextI18n")
    private fun updateTotalPrice() {
        val totalPrice = clothingVariants
            .firstOrNull { it.size == selectedSize }
            ?.let { it.price * currentQuantity }
            ?: 0.0

        val formattedPrice = String.format("%.2f", totalPrice)
        binding?.tvTotalPriceInDollars?.text = "$$formattedPrice"

        val deliveryPrice = 10 // will be fixed
        val finalAmount = totalPrice + deliveryPrice
        val formattedFinalAmount = String.format("%.2f", finalAmount)
        binding?.tvFinalAmountInDollars?.text = "$$formattedFinalAmount"
    }


    private fun updateButtonStates() {
        binding?.btnPlus?.isEnabled = currentQuantity < maxQuantity
        binding?.btnMinus?.isEnabled = currentQuantity > 0
    }

    private fun readJsonDataFromRawResource(resourceId: Int): String {
        val inputStream = resources.openRawResource(resourceId)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val stringBuilder = StringBuilder()
        var line: String?
        while (reader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
        return stringBuilder.toString()
    }
}