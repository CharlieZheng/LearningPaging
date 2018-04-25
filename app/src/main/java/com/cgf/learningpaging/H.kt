package com.cgf.learningpaging

import android.support.v7.widget.RecyclerView
import com.cgf.learningpaging.databinding.ItemEntityBinding

class H(val ding: ItemEntityBinding) : RecyclerView.ViewHolder(ding.root) {
    fun onBindViewHolder(position: Int, item: Entity?) {
        ding.name.text = item?.province
    }
}