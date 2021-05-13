package com.example.qiwi.presentation.holder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.aldredo.core.base.holder.BaseHolder
import com.example.qiwi.R
import com.example.qiwi.domain.dto.ViewDto
import com.example.qiwi.presentation.viewModel.QiwiViewModel

class SpinnerHolder(
    private val view: View,
    private val viewModel: QiwiViewModel
) : BaseHolder<ViewDto>(view) {
    private val adapter: ArrayAdapter<String> by lazy {
        ArrayAdapter(
            view.context,
            android.R.layout.simple_spinner_item,
            viewDto.widget.toArrayTitle()
        ).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
    }

    private lateinit var viewDto: ViewDto
    private val title = view.findViewById<TextView>(R.id.title)
    private val spinner = view.findViewById<Spinner>(R.id.spinner).apply {
        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                viewModel.selectItem(
                    viewDto.widget.choices[position].value,
                    viewDto
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    override fun bind(data: ViewDto) {
        this.viewDto = data
        title.text = data.title
        spinner.adapter = adapter
    }

    companion object {
        fun createSpinnerHolder(parent: ViewGroup, navigation: QiwiViewModel): BaseHolder<ViewDto> {
            val inflateView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_spiner, parent, false)
            return SpinnerHolder(inflateView, navigation)
        }
    }
}

