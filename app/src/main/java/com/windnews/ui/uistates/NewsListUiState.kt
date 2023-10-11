package com.windnews.ui.uistates

import com.windnews.data.model.Article


sealed class NewsListUiState {
    data class NewsItem(val news: Article) : NewsListUiState()

    data class DateHeader(val date: String) : NewsListUiState()
}
