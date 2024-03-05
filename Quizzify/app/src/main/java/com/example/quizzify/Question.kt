package com.example.quizzify

import androidx.annotation.StringRes


data class Question(@StringRes val textResId: Int, val answer: Boolean)