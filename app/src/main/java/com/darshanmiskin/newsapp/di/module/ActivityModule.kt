package com.darshanmiskin.newsapp.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.darshanmiskin.newsapp.data.api.NetworkService
import com.darshanmiskin.newsapp.data.repository.NewsRepository
import com.darshanmiskin.newsapp.di.ActivityContext
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun providesContext(): Context = activity

    @Provides
    fun providesNewsRepository(networkService: NetworkService) = NewsRepository(networkService)
}