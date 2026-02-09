package com.darshanmiskin.newsapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.darshanmiskin.newsapp.R

abstract class BaseActivity<VB : ViewDataBinding>() : AppCompatActivity() {

    companion object {
        fun <T> Context.startActivity(clazz: Class<T>, intent: Intent? = null) =
            this@startActivity.startActivity(Intent(this@startActivity, clazz).also { thisIntent ->
                intent?.let { thisIntent.putExtras(intent) }
            })
    }

    protected lateinit var binding: VB

    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutId)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}