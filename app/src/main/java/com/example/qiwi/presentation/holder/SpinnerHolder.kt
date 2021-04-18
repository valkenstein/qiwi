package com.example.qiwi.presentation.holder

import android.view.View
import android.widget.*
import com.aldredo.core.base.holder.BaseHolder
import com.example.qiwi.R
import com.example.qiwi.domain.dto.ViewDto
import com.example.qiwi.presentation.viewModel.QiwiViewModel

class SpinnerHolder(
    private val view: View,
    private val currentList: MutableList<ViewDto>,
    private val viewModel: QiwiViewModel
) : BaseHolder(view) {
    private val title = view.findViewById<TextView>(R.id.title)
    private val spinner = view.findViewById<Spinner>(R.id.spinner)
    init {
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                viewModel.selectItem(
                    currentList[bindingAdapterPosition].widget.choices[position].value,
                    currentList[bindingAdapterPosition]
                )
            }
            override fun onNothingSelected(parent: AdapterView<*>) { }
        }
    }

    override fun bind() {
        title.text = currentList[bindingAdapterPosition].title
        val adapter = ArrayAdapter(
            view.context,
            android.R.layout.simple_spinner_item,
            currentList[bindingAdapterPosition].widget.toArrayTitle()
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}

