package com.darshanmiskin.newsapp.ui.search

import android.os.Bundle
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.databinding.ActivitySearchBinding
import com.darshanmiskin.newsapp.ui.base.BaseActivity

class SearchActivity : BaseActivity<ActivitySearchBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_search

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}