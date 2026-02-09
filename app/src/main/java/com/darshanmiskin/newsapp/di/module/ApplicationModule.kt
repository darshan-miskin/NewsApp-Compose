package com.darshanmiskin.newsapp.di.module

import android.content.Context
import com.darshanmiskin.newsapp.NewsApplication
import com.darshanmiskin.newsapp.data.api.HeaderInterceptor
import com.darshanmiskin.newsapp.data.api.NetworkService
import com.darshanmiskin.newsapp.di.ApplicationContext
import com.darshanmiskin.newsapp.di.BaseUrl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
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
    fun providesOkHttpClient() = OkHttpClient.Builder().addInterceptor(HeaderInterceptor()).build()

    @Singleton
    @Provides
    fun providesRetrofit(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
            .addConverterFactory(gsonConverterFactory).build()
    }

    @Singleton
    @Provides
    fun providesNetworkService(retrofit: Retrofit) = retrofit.create(NetworkService::class.java)

}