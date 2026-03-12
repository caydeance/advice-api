package com.example.adviceapi

data class AdviceCollection(
    val total_results: String,
    val query: String,
    val slips: List<Slip>
)

data class Slip(
    val id: Int,
    val advice: String
)
