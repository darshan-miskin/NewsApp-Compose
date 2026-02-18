package com.darshanmiskin.newsapp.ui.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.darshanmiskin.newsapp.R

abstract class BaseActivity: ComponentActivity() {

    companion object {
        fun <T> Context.startActivity(clazz: Class<T>, intent: Intent? = null) =
            this@startActivity.startActivity(Intent(this@startActivity, clazz).also { thisIntent ->
                intent?.let { thisIntent.putExtras(intent) }
            })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @Composable
    fun Loading(retryClick: () -> Unit){
        Box(modifier = Modifier.fillMaxWidth()) {
            Column {
                Error(retryClick)
                NoDataFound()
                CircularProgressIndicator()
            }
        }
    }

    @Composable
    fun Error(retryClick: () -> Unit){
        Text(text = getString(R.string.oops), fontSize = 24.sp)
        Text(text = getString(R.string.something_went_wrong_let_s_try_once_again))
        Button(onClick = retryClick) {
            Text(text = getString(R.string.try_again))
        }
    }

    @Composable
    fun NoDataFound(){
        Text(text = getString(R.string.no_data_found), fontSize = 24.sp)
    }

    @Preview
    @Composable
    fun LoadingPreview(){
        Loading({})
    }
}