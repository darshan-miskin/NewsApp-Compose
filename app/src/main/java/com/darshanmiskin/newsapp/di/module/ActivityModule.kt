package com.darshanmiskin.newsapp.di.module

import com.darshanmiskin.newsapp.data.api.NetworkService
import com.darshanmiskin.newsapp.data.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(value = [ActivityComponent::class])
@Module
class ActivityModule {
    @Provides
    fun providesNewsRepository(networkService: NetworkService) = NewsRepository(networkService)
}