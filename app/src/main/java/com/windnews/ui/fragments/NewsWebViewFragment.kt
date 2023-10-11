package com.windnews.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.windnews.R
import com.windnews.databinding.NewsWebvewLayoutBinding

class NewsWebViewFragment: Fragment() {
    private var newsUrl: String? = null
    private var _binding: NewsWebvewLayoutBinding? = null
    private val binding get() = _binding

    companion object {
        const val TAG = "NewsWebViewFragment"
        private const val URL_PARAM = "url_param"

        fun newInstance(url: String): NewsWebViewFragment {
            return NewsWebViewFragment().apply {
                val bundle = bundleOf()

                bundle.putString(URL_PARAM, url)
                arguments = bundle
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getBundleData()
    }


    private fun getBundleData() {
        arguments?.let {
            newsUrl = it.getString(URL_PARAM)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = NewsWebvewLayoutBinding.inflate(inflater, container, false)
        binding?.root?.setOnClickListener {  }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWebView()
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupWebView(){
        binding?.webView?.settings?.javaScriptEnabled = true

        newsUrl?.let { binding?.webView?.loadUrl(it) }
        binding?.webView?.webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding?.loader?.hide()

                Log.e("webview", "onPageFinished url $url")
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)

                binding?.loader?.hide()

                showSnackBar()

                Log.e("webview", "onReceivedError error $error")
            }
        }
        binding?.webView?.webChromeClient = WebChromeClient()
    }

    private fun showSnackBar() {
        binding?.webView?.let {
            Snackbar.make(it, R.string.something_went_wrong_description, Snackbar.LENGTH_SHORT)
                .show()
        }
    }
}