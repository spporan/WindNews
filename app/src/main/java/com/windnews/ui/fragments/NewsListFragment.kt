package com.windnews.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.windnews.R
import com.windnews.data.model.Article
import com.windnews.databinding.NewsListFragmentBinding
import com.windnews.ui.adapters.NewsListAdapter
import com.windnews.ui.adapters.NewsListItemClickListener
import com.windnews.ui.viewmodels.NewsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewsListFragment: Fragment(), NewsListItemClickListener {
    companion object {
        const val TAG = "NewsListFragment"

        fun newInstance() = NewsListFragment()
    }
    private var _binding: NewsListFragmentBinding? = null
    private val binding get() = _binding

    private val viewModel: NewsListViewModel by viewModels()
    @Inject
    lateinit var newsAdapter: NewsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NewsListFragmentBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.newsFlow.collectLatest { pageData ->
                    newsAdapter.submitData(pageData)
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
    }

    private fun initView() {
        newsAdapter.clickListener = this
        newsAdapter.onRetry = {
            newsAdapter.retry()
        }

        binding?.newsList?.apply {
            layoutManager =  LinearLayoutManager(context)
            adapter  =  newsAdapter
        }

        //page  state observer
        newsAdapter.addLoadStateListener { loadState ->

            val isEmptyData = newsAdapter.itemCount == 0
            val isError = loadState.refresh is LoadState.Error && isEmptyData
            val isEmptyDataState = loadState.mediator?.append is LoadState.NotLoading
                    && isEmptyData
                    && loadState.mediator?.append?.endOfPaginationReached == true

            if (loadState.refresh is LoadState.Loading && isEmptyData){
                binding?.loader?.visibility = View.VISIBLE
            }
            else if (isError || isEmptyDataState) {
                // getting the error and gone loader
                binding?.errorMsg?.isVisible = true
                binding?.retryButton?.isVisible = true

                binding?.loader?.isVisible = false
            } else {
                //gone error placeholder here
                binding?.loader?.isVisible = isEmptyData
                binding?.errorMsg?.isVisible = false
                binding?.retryButton?.isVisible = false
            }
            binding?.retryButton?.setOnClickListener {
                newsAdapter.retry()

                binding?.errorMsg?.isVisible = false
                binding?.retryButton?.isVisible = false
            }
        }
    }

    override fun gotoDetails(article: Article) {
        gotoNewsDetails(article)

    }

    private fun gotoNewsDetails(article: Article) {
        val newDetails = NewsDetailsFragment.newInstance(article)

        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(R.id.container, newDetails, NewsDetailsFragment.TAG)
            .addToBackStack(NewsDetailsFragment.TAG)
            .commit()
    }
}