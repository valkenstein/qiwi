package com.aldredo.core.base.adapter

import androidx.recyclerview.widget.RecyclerView
import com.aldredo.core.base.holder.BaseHolder

//abstract class BaseRecyclerAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    var currentList: MutableList<T> = arrayListOf()
//
//
//    var defaultCount: Int = 0
//
//    open fun submitList(listData: MutableList<T>) {
//        currentList = listData
//    }
//
//    override fun getItemCount() = if (currentList.isEmpty()) defaultCount else currentList.count()
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        (holder as BaseHolder).bind()
//    }
//}