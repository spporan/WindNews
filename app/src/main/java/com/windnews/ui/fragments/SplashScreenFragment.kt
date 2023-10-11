package com.windnews.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.windnews.R
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SplashFragment : Fragment(), CoroutineScope {

    companion object {
        const val TAG = "SplashFragment"

        fun newInstance() = SplashFragment()
    }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + Job()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.splash_screen_layout, container, false)
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onPause() {
        super.onPause()
        (requireActivity() as AppCompatActivity).supportActionBar?.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        launch {
            delay(2000)
            withContext(Dispatchers.Main){
                Log.e("Tag", "Goto next screen")
                gotoNewsList()
            }
        }
    }

    private fun gotoNewsList() {
        val newList = NewsListFragment.newInstance()

        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, newList, NewsListFragment.TAG)
            .commit()
    }
}