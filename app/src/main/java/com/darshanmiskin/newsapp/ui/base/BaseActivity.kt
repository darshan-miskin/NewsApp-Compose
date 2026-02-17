package com.darshanmiskin.newsapp.ui.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.darshanmiskin.newsapp.databinding.LayoutLoadingBinding

abstract class BaseActivity<VB : ViewDataBinding>() : AppCompatActivity() {

    companion object {
        fun <T> Context.startActivity(clazz: Class<T>, intent: Intent? = null) =
            this@startActivity.startActivity(Intent(this@startActivity, clazz).also { thisIntent ->
                intent?.let { thisIntent.putExtras(intent) }
            })
    }

    protected lateinit var binding: VB

    protected val layoutProgress by lazy {
        LayoutLoadingBinding.bind(binding.root)
    }

//    protected lateinit var activityComponent: ActivityComponent

    @get:LayoutRes
    protected abstract val layoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun hideKeyboard() {
        (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            binding.root.windowToken,
            0
        )
    }
}