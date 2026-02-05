package com.darshanmiskin.newsapp

import android.app.Application
import com.darshanmiskin.newsapp.di.component.ApplicationComponent
import com.darshanmiskin.newsapp.di.component.DaggerApplicationComponent
import com.darshanmiskin.newsapp.di.module.ApplicationModule

class NewsApplication : Application() {

    private val baseUrl = BuildConfig.BASE_URL
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    fun injectDependencies(){
        applicationComponent =
            DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this, baseUrl))
                .build()
        applicationComponent.inject(this)
    }
}