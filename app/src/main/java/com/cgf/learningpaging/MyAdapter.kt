package com.cgf.learningpaging

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class MyAdapter : PagedListAdapter<Entity, RecyclerView.ViewHolder>(cb) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return H(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_entity, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is H) {
            holder.onBindViewHolder(position, getItem(position))
        }
    }

    companion object {
        val cb = object : DiffUtil.ItemCallback<Entity>() {
            override fun areItemsTheSame(oldItem: Entity?, newItem: Entity?): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Entity?, newItem: Entity?): Boolean {
                return oldItem?.province == newItem?.province
            }
        }
    }
}