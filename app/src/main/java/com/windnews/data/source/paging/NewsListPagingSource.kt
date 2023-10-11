package com.windnews.data.source.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.windnews.data.model.Article
import com.windnews.data.source.remote.NewsListRemoteDataSource
import com.windnews.data.utils.getFormattedDateTime
import com.windnews.data.utils.toDateTime
import com.windnews.ui.uistates.NewsListUiState
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Named

class NewsListPagingSource @Inject constructor(
     private val newsListRemoteDataSource: NewsListRemoteDataSource,
     private val apiKey: String
) : PagingSource<Int, NewsListUiState>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, NewsListUiState> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = newsListRemoteDataSource.fetchNewsList(apiKey,nextPageNumber)

            val groupBy = response.articles.groupBy { it.publishedAt.getFormattedDateTime() }
            Log.e("source", "map1 group by $groupBy")
            val newsItemAndDateHeader = mutableListOf<NewsListUiState>()
            groupBy.forEach { (key, values) ->
                key?.let {
                    newsItemAndDateHeader.add(NewsListUiState.DateHeader(key))
                }
                values.map { article->
                    newsItemAndDateHeader.add(NewsListUiState.NewsItem(article))
                }
            }

            Log.e("source", "Page<>  $nextPageNumber")
            Log.e("source", "Page<> data  $groupBy")

            return LoadResult.Page(
                data = newsItemAndDateHeader,
                prevKey = null,
                nextKey = nextPageNumber + 1
            )
        }catch (e: IOException) {
            // IOException for network failures.
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, NewsListUiState>): Int? {
        // Try to find the page key of the closest page to anchorPosition from
        // either the prevKey or the nextKey; you need to handle nullability
        // here.
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey are null -> anchorPage is the
        //    initial page, so return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
