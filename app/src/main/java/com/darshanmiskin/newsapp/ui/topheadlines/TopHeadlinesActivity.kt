package com.darshanmiskin.newsapp.ui.topheadlines

import android.content.Intent
import android.os.Bundle
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.databinding.ActivityTopHeadlinesBinding
import com.darshanmiskin.newsapp.ui.base.BaseActivity

class TopHeadlinesActivity : BaseActivity<ActivityTopHeadlinesBinding>() {

    companion object {
        fun createIntent(filter: Filter) = Intent().putExtra("Filter", filter.name)

    }
    enum class Filter {
        COUNTRY, LANGUAGE, SOURCE, TOP_HEADLINES
    }

    override val layoutId: Int
        get() = R.layout.activity_top_headlines

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val filter = Filter.valueOf(intent.extras?.getString("Filter")?:Filter.TOP_HEADLINES.name)
        title = "Filter of: $filter"


    }
}