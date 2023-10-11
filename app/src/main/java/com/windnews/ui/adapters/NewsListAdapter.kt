package com.windnews.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.windnews.databinding.DateHeaderItemBinding
import com.windnews.databinding.NewsItemViewBinding
import com.windnews.ui.adapters.viewholders.DateHeaderViewHolder
import com.windnews.ui.adapters.viewholders.NewsListItemViewHolder
import com.windnews.ui.uistates.NewsListUiState
import javax.inject.Inject

class NewsListAdapter @Inject constructor():
    PagingDataAdapter<NewsListUiState, ViewHolder>(diffCallback) {
    var onRetry: (() -> Unit)? = null
    var clickListener: NewsListItemClickListener? = null

    companion object {
        const val DATE_HEADER = 3042
        const val NEWS_ITEM = 3032

        private val diffCallback = object : DiffUtil.ItemCallback<NewsListUiState>() {
            override fun areItemsTheSame(oldItem: NewsListUiState, newItem: NewsListUiState): Boolean {
                return (oldItem as NewsListUiState.NewsItem).hashCode() == (newItem as NewsListUiState.NewsItem).hashCode()
            }

            override fun areContentsTheSame(oldItem: NewsListUiState, newItem: NewsListUiState): Boolean {
                return (oldItem as NewsListUiState.NewsItem) == (newItem as NewsListUiState.NewsItem)
            }
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return when(viewType) {
            NEWS_ITEM -> {
                val binding = NewsItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                 NewsListItemViewHolder(binding)
            }
            else -> {
                val binding = DateHeaderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                 DateHeaderViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)) {
            is NewsListUiState.NewsItem -> NEWS_ITEM
            else -> DATE_HEADER
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        when(holder) {
            is NewsListItemViewHolder -> {
                holder.onBind(item as NewsListUiState.NewsItem, clickListener)
            }
            is DateHeaderViewHolder -> {
                holder.onBind(item as NewsListUiState.DateHeader)
            }
        }

    }
}
