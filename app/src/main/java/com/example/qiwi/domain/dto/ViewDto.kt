package com.example.qiwi.domain.dto

data class ViewDto(
    val title: String,
    val widget: WidgetDto,
    val validator: ValidatorDto,
    val getViewType: Int
)

