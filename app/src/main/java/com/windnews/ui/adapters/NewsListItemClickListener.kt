package com.windnews.ui.adapters

import com.windnews.data.model.Article

interface NewsListItemClickListener {
    fun gotoDetails(article: Article)
}