package com.abuenoben.challenge.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.abuenoben.challenge.R
import kotlinx.android.synthetic.main.cell_favorite.view.*


class FavoritesAdapter(
    val onClick: ((response: String) -> Unit)? = null,
    val onClickRemove: ((response: String) -> Unit)? = null
) : ListAdapter<String, FavoritesAdapter.StringViewHolder>(Diff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return StringViewHolder(inflater.inflate(R.layout.cell_favorite, parent, false))
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class StringViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(response: String) {
            itemView.setOnClickListener { onClick?.invoke(response) }
            itemView.removeButton.setOnClickListener { onClickRemove?.invoke(response) }
            with(itemView.title) { text = response }
        }
    }

    class Diff : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(old: String, new: String): Boolean = old == new
        override fun areContentsTheSame(old: String, new: String): Boolean = old == new
    }
}