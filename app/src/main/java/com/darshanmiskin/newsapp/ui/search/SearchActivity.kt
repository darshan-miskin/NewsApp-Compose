package com.darshanmiskin.newsapp.ui.search

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.databinding.ActivitySearchBinding
import com.darshanmiskin.newsapp.ui.BaseActivity

class SearchActivity : BaseActivity<ActivitySearchBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_search

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}