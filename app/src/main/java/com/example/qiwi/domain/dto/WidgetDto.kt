package com.example.qiwi.domain.dto

data class WidgetDto(
    val type: String,
    val choices: List<ChoicesDto>
) {
    fun toArrayTitle(): Array<String> {
        val list = ArrayList<String>()
        choices.forEach {
            list.add(it.title)
        }
        return list.toTypedArray()
    }
}