package com.darshanmiskin.newsapp.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.darshanmiskin.newsapp.data.api.NetworkService
import com.darshanmiskin.newsapp.data.repository.NewsRepository
import com.darshanmiskin.newsapp.di.ActivityContext
import com.darshanmiskin.newsapp.ui.base.ViewModelFactory
import com.darshanmiskin.newsapp.ui.countries.CountriesViewModel
import com.darshanmiskin.newsapp.ui.languages.LanguagesViewModel
import com.darshanmiskin.newsapp.ui.newssources.NewsSourceViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun providesContext(): Context = activity

    @Provides
    fun providesNewsRepository(networkService: NetworkService) = NewsRepository(networkService)

    @Provides
    fun providesCountriesViewModel(repository: NewsRepository): CountriesViewModel {
        return ViewModelProvider(
            activity,
            ViewModelFactory(CountriesViewModel::class.java)
            { CountriesViewModel(repository) })[CountriesViewModel::class.java]
    }

    @Provides
    fun providesLanguagesViewModel(repository: NewsRepository): LanguagesViewModel {
        return ViewModelProvider(
            activity,
            ViewModelFactory(LanguagesViewModel::class.java) {
                LanguagesViewModel(repository)
            }
        )[LanguagesViewModel::class.java]
    }

    @Provides
    fun providesNewsSourceViewModel(repository: NewsRepository): NewsSourceViewModel {
        return ViewModelProvider(
            activity,
            ViewModelFactory(NewsSourceViewModel::class.java) {
                NewsSourceViewModel(repository)
            }
        )[NewsSourceViewModel::class.java]
    }
}