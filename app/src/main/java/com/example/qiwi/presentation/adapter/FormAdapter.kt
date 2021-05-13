package com.example.qiwi.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aldredo.core.base.adapter.BaseListAdapter
import com.aldredo.core.base.holder.BaseHolder
import com.example.qiwi.R
import com.example.qiwi.di.ActivityScope
import com.example.qiwi.domain.dto.ViewDto
import com.example.qiwi.presentation.holder.SpinnerHolder
import com.example.qiwi.presentation.holder.TextHolder
import com.example.qiwi.presentation.viewModel.QiwiViewModel
import javax.inject.Inject

@ActivityScope
class FormAdapter @Inject constructor(private val navigation: QiwiViewModel) :
    BaseListAdapter<ViewDto>(DIFF_CALLBACK) {
    private val mapHolder = HashMap<Int, (ViewGroup, QiwiViewModel) -> BaseHolder<ViewDto>>()

    init {
        mapHolder[0] = SpinnerHolder::createSpinnerHolder
        mapHolder[1] = TextHolder::createTextHolder
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return mapHolder[viewType]?.invoke(parent, navigation) ?: TextHolder.createTextHolder(
            parent,
            navigation
        )
    }

    override fun getItemViewType(position: Int) = currentList[position].getViewType

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ViewDto>() {

            override fun areItemsTheSame(oldItem: ViewDto, newItem: ViewDto): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: ViewDto, newItem: ViewDto): Boolean =
                oldItem.widget == newItem.widget

        }
    }
}