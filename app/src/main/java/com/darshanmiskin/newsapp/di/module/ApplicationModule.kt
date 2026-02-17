package com.darshanmiskin.newsapp.di.module

import com.darshanmiskin.newsapp.BuildConfig
import com.darshanmiskin.newsapp.data.api.HeaderInterceptor
import com.darshanmiskin.newsapp.data.api.NetworkService
import com.darshanmiskin.newsapp.di.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(value = [SingletonComponent::class])
@Module
class ApplicationModule {
    @BaseUrl
    @Provides
    fun providesBaseUrl(): String = BuildConfig.BASE_URL

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
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory).build()
    }

    @Singleton
    @Provides
    fun providesNetworkService(retrofit: Retrofit) = retrofit.create(NetworkService::class.java)

}