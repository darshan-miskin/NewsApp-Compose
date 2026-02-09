package com.darshanmiskin.newsapp.ui.newssources

import android.os.Bundle
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.databinding.ActivityNewsSourceBinding
import com.darshanmiskin.newsapp.ui.BaseActivity

class NewsSourceActivity : BaseActivity<ActivityNewsSourceBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_news_source

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}