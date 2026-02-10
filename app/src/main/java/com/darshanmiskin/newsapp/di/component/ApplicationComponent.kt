package com.darshanmiskin.newsapp.di.component

import android.content.Context
import com.darshanmiskin.newsapp.NewsApplication
import com.darshanmiskin.newsapp.data.api.NetworkService
import com.darshanmiskin.newsapp.di.ApplicationContext
import com.darshanmiskin.newsapp.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: NewsApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNewsService(): NetworkService
}