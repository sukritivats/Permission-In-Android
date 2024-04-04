package com.example.assignment.DataClass

import com.example.assignment.DataClass.AvailableColor

data class ClothingVariant(
    val available_colors: List<AvailableColor>,
    val name: String,
    val price: Double,
    val quantity: Int,
    val size: String,
    val variant_id: Int
)