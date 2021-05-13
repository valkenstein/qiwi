package com.example.qiwi.presentation.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.aldredo.core.base.holder.BaseHolder
import com.example.qiwi.R
import com.example.qiwi.domain.dto.ViewDto
import com.example.qiwi.presentation.viewModel.QiwiViewModel

class TextHolder(
    view: View,
    private val viewModel: QiwiViewModel
) : BaseHolder<ViewDto>(view) {
    private val title = view.findViewById<TextView>(R.id.title)
    private val input = view.findViewById<TextView>(R.id.input)
    private var viewDto: ViewDto? = null

    init {
        input.addTextChangedListener {
            viewModel.selectItem(
                it.toString(),
                viewDto
            )
        }
    }

    override fun bind(data: ViewDto) {
        this.viewDto = data
        title.text = data.title
    }
    companion object {
        fun createTextHolder(parent: ViewGroup, navigation: QiwiViewModel): BaseHolder<ViewDto> {
            val inflateView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_edit, parent, false)
            return TextHolder(inflateView, navigation)
        }
    }
}