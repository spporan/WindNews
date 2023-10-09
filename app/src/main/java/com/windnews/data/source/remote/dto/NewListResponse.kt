package com.windnews.data.source.remote.dto

import com.google.gson.annotations.SerializedName
import com.windnews.data.model.Article

/**
 * Data class to hold news list response from  API calls.
 */
data class NewListResponse(
    @SerializedName("status") val status: String,
    @SerializedName("articles") val articles: List<Article>,
    @SerializedName("totalResults") val totalResults: Int = 0,
    )