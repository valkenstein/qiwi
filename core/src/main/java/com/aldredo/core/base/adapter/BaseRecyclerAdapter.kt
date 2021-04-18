package com.aldredo.core.base.adapter

import androidx.recyclerview.widget.RecyclerView
import com.aldredo.core.base.holder.BaseHolder

abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    protected var currentList: List<T> = emptyList()

    var defaultCount: Int = 1

    open fun submitList(listData: List<T>) {
        currentList = listData
    }

    override fun getItemCount() = if (currentList.isEmpty()) defaultCount else currentList.count()

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BaseHolder).bind()
    }
}