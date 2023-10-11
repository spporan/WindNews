package com.windnews.ui.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.windnews.databinding.DateHeaderItemBinding
import com.windnews.databinding.NewsItemViewBinding
import com.windnews.ui.uistates.NewsListUiState

class DateHeaderViewHolder(
    private val binding: DateHeaderItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun onBind(newUiItem: NewsListUiState.DateHeader) {
        binding.apply {
            dateTv.text = newUiItem.date
        }
    }
}