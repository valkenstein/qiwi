package com.example.qiwi.data.model

data class Validator(
    val type: String?,
    val message: String?,
    val predicate: Predicate?,
)