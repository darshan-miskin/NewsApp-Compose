package com.darshanmiskin.newsapp.ui.languages

import android.os.Bundle
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.databinding.ActivityLanguagesBinding
import com.darshanmiskin.newsapp.ui.BaseActivity

class LanguagesActivity : BaseActivity<ActivityLanguagesBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_languages

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}