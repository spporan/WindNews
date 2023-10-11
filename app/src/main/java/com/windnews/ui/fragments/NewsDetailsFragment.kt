package com.windnews.ui.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.windnews.R
import com.windnews.data.model.Article
import com.windnews.data.utils.getFormattedDateTime
import com.windnews.databinding.NewsArticleDetailsLayoutBinding
import com.windnews.ui.utils.catchLinkClick

class NewsDetailsFragment() : Fragment()  {
    private var article: Article? = null
    private var _binding: NewsArticleDetailsLayoutBinding? = null
    private val binding get() = _binding

    companion object {
        const val TAG = "NewsDetailsFragment"
        private const val ARTICLE_PARAM = "article_param"

        fun newInstance(article: Article): NewsDetailsFragment {
            return NewsDetailsFragment().apply {
                val bundle = bundleOf()

                bundle.putParcelable(ARTICLE_PARAM, article)
                arguments = bundle
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getBundleData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NewsArticleDetailsLayoutBinding.inflate(inflater, container, false)
        binding?.root?.setOnClickListener { /*Prevent click action to underneath fragment*/ }

        return binding?.root
    }

    private fun getBundleData() {
        arguments?.let {
            article = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                it.getParcelable(ARTICLE_PARAM, Article::class.java)
            } else {
                it.getParcelable(ARTICLE_PARAM)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onPause() {
        super.onPause()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun initView() {
        binding?.apply {
            article ?: return@apply

            title.text = article?.title
            description.text = article?.description
            content.text = article?.content
            urlTv.text = article?.url
            timeTv.text = article?.publishedAt?.getFormattedDateTime("dd MMMM yyyy HH:mm")
            Glide.with(requireActivity()).load(article?.urlToImage).into(image)

            urlTv.catchLinkClick{
                gotoNewsWebView(it)
                Log.e("webVIew", "go to WebView $it")
            }
        }
    }

    private fun gotoNewsWebView(url: String) {
        val newDetails = NewsWebViewFragment.newInstance(url)

        requireActivity().supportFragmentManager
            .beginTransaction()
            .add(R.id.container, newDetails, NewsWebViewFragment.TAG)
            .addToBackStack(NewsWebViewFragment.TAG)
            .commit()
    }


}