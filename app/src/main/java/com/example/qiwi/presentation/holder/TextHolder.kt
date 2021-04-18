package com.example.qiwi.presentation.holder

import android.view.View
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.aldredo.core.base.holder.BaseHolder
import com.example.qiwi.R
import com.example.qiwi.domain.dto.ViewDto
import com.example.qiwi.presentation.viewModel.QiwiViewModel

class TextHolder(
    view: View,
    private val currentList: MutableList<ViewDto>,
    private val viewModel: QiwiViewModel
) : BaseHolder(view) {
    private val title = view.findViewById<TextView>(R.id.title)
    private val input = view.findViewById<TextView>(R.id.input)

    init {
        input.addTextChangedListener {
            viewModel.selectItem(
                it.toString(),
                currentList[bindingAdapterPosition]
            )
        }

    }

    override fun bind() {
        title.text = currentList[bindingAdapterPosition].title
    }
}