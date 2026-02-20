package com.darshanmiskin.newsapp.ui.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity

abstract class BaseActivity : ComponentActivity() {

    companion object {
        fun <T> Context.startActivity(clazz: Class<T>, intent: Intent? = null) =
            this@startActivity.startActivity(Intent(this@startActivity, clazz).also { thisIntent ->
                intent?.let { thisIntent.putExtras(intent) }
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}