package com.windnews.ui.adapters.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.windnews.databinding.NewsItemViewBinding
import com.windnews.ui.adapters.NewsListItemClickListener
import com.windnews.ui.uistates.NewsListUiState

class NewsListItemViewHolder(
    private val binding: NewsItemViewBinding
): RecyclerView.ViewHolder(binding.root) {

    fun onBind(newUiItem: NewsListUiState.NewsItem, clickListener: NewsListItemClickListener?) {
        binding.apply {
            Glide.with(itemView.context).load(newUiItem.news.urlToImage).into(image)
            title.text = newUiItem.news.title
            description.text = newUiItem.news.description
        }
        itemView.setOnClickListener { clickListener?.gotoDetails(newUiItem.news) }
    }
}