package com.example.qiwi.data.model

data class ResultForm(val content: Form?) {
    data class Form(val elements: List<ItemForm>?) {
        data class ItemForm(
            val type: String?,
            val name: String?,
            val validator: Validator?,
            val condition: Condition?,
            val view: View?,
            val content: Form?,
        )
    }
}

