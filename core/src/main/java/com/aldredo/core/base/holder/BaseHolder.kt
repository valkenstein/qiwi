package com.aldredo.core.base.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseHolder<H>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(data: H)
}