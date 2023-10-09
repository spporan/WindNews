package com.windnews.data.source.remote.api

import com.windnews.data.source.remote.dto.NewListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    /**
     * Get news list from remote service by paginated way.
     */
    @GET("/everything?q=usdc&sortBy=publishedAt")
    suspend fun getAndroidRepos(
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int,
        @Query("pageSize") itemsPerPage: Int
    ): NewListResponse
}