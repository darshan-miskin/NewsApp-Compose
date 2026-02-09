package com.darshanmiskin.newsapp.ui.topheadlines

import android.os.Bundle
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.databinding.ActivityTopHeadlinesBinding
import com.darshanmiskin.newsapp.ui.BaseActivity

class TopHeadlinesActivity : BaseActivity<ActivityTopHeadlinesBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_top_headlines

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}