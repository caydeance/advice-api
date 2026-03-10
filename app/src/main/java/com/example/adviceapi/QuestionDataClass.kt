package com.example.adviceapi

data class QuestionDataClass(
    val question: String,
    val choices: Map<String, Int>
)
