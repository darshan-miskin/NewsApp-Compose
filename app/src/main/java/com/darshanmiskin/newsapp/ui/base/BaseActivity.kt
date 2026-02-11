package com.darshanmiskin.newsapp.ui.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.darshanmiskin.newsapp.NewsApplication
import com.darshanmiskin.newsapp.R
import com.darshanmiskin.newsapp.di.component.ActivityComponent
import com.darshanmiskin.newsapp.di.component.DaggerActivityComponent
import com.darshanmiskin.newsapp.di.module.ActivityModule

abstract class BaseActivity<VB : ViewDataBinding>() : AppCompatActivity() {

    companion object {
        fun <T> Context.startActivity(clazz: Class<T>, intent: Intent? = null) =
            this@startActivity.startActivity(Intent(this@startActivity, clazz).also { thisIntent ->
                intent?.let { thisIntent.putExtras(intent) }
            })
    }

    protected lateinit var binding: VB

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

//    fun <VB: ViewDataBinding, A: BaseActivity<VB>> injectDependencies(activity: A) {
//        activityComponent =
//            DaggerActivityComponent.builder()
//                .applicationComponent((application as NewsApplication).applicationComponent)
//                .activityModule(ActivityModule(activity))
//                .build()
//        activityComponent.inject(activity)
//    }

}