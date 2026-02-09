package com.darshanmiskin.newsapp.ui.countries

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.databinding.ActivityCountriesBinding
import com.darshanmiskin.newsapp.ui.BaseActivity

class CountriesActivity : BaseActivity<ActivityCountriesBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_countries

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}