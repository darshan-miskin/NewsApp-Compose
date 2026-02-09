package com.darshanmiskin.newsapp.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.databinding.ActivityMainBinding
import com.darshanmiskin.newsapp.ui.BaseActivity
import com.darshanmiskin.newsapp.ui.countries.CountriesActivity
import com.darshanmiskin.newsapp.ui.languages.LanguagesActivity
import com.darshanmiskin.newsapp.ui.newssources.NewsSourceActivity
import com.darshanmiskin.newsapp.ui.search.SearchActivity
import com.darshanmiskin.newsapp.ui.topheadlines.TopHeadlinesActivity

class MainActivity : BaseActivity<ActivityMainBinding>(), View.OnClickListener {

    override val layoutId: Int get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_top_headlines -> startActivity(TopHeadlinesActivity::class.java)
            R.id.btn_news_sources -> startActivity(NewsSourceActivity::class.java)
            R.id.btn_countries -> startActivity(CountriesActivity::class.java)
            R.id.btn_languages -> startActivity(LanguagesActivity::class.java)
            R.id.btn_search -> startActivity(SearchActivity::class.java)
            else -> {
                startActivity(SearchActivity::class.java)
            }
        }
    }
}