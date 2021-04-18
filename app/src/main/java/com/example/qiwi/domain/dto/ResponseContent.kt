package com.example.qiwi.domain.dto

data class ResponseContent(
    val visibleView: MutableList<ViewDto>,
    val hideView: HashMap<String, ArrayList<ViewDto>>
)