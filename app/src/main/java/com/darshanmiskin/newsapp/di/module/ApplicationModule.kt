package com.darshanmiskin.newsapp.di.module

import android.content.Context
import com.darshanmiskin.newsapp.NewsApplication
import com.darshanmiskin.newsapp.data.api.NetworkService
import com.darshanmiskin.newsapp.di.ApplicationContext
import com.darshanmiskin.newsapp.di.BaseUrl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: NewsApplication, private val baseUrl: String) {

    @ApplicationContext
    @Provides
    fun providesContext(): Context = application

    @BaseUrl
    @Provides
    fun providesBaseUrl(): String = baseUrl

    @Singleton
    @Provides
    fun providesGsonConverterFactory() = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun providesRetrofit(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(gsonConverterFactory).build()
    }

    @Singleton
    @Provides
    fun providesNetworkService(retrofit: Retrofit) = retrofit.create(NetworkService::class.java)

}