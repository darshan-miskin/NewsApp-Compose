package com.darshanmiskin.newsapp.di.module

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.darshanmiskin.newsapp.data.api.NetworkService
import com.darshanmiskin.newsapp.data.repository.NewsRepository
import com.darshanmiskin.newsapp.ui.base.ViewModelFactory
import com.darshanmiskin.newsapp.ui.countries.CountriesViewModel
import com.darshanmiskin.newsapp.ui.languages.LanguagesViewModel
import com.darshanmiskin.newsapp.ui.newssources.NewsSourceViewModel
import com.darshanmiskin.newsapp.ui.search.SearchViewModel
import com.darshanmiskin.newsapp.ui.topheadlines.TopHeadlineViewModel
import com.darshanmiskin.newsapp.ui.topheadlines.TopHeadlinesActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(value = [ActivityComponent::class])
@Module
class ActivityModule{

    @Provides
    fun providesNewsRepository(networkService: NetworkService) = NewsRepository(networkService)

//    @Provides
//    fun providesCountriesViewModel(repository: NewsRepository): CountriesViewModel {
//        return ViewModelProvider(
//            activity,
//            ViewModelFactory(CountriesViewModel::class.java)
//            { CountriesViewModel(repository) })[CountriesViewModel::class.java]
//    }
//
//    @Provides
//    fun providesLanguagesViewModel(repository: NewsRepository): LanguagesViewModel {
//        return ViewModelProvider(
//            activity,
//            ViewModelFactory(LanguagesViewModel::class.java) {
//                LanguagesViewModel(repository)
//            }
//        )[LanguagesViewModel::class.java]
//    }
//
//    @Provides
//    fun providesNewsSourceViewModel(repository: NewsRepository): NewsSourceViewModel {
//        return ViewModelProvider(
//            activity,
//            ViewModelFactory(NewsSourceViewModel::class.java) {
//                NewsSourceViewModel(repository)
//            }
//        )[NewsSourceViewModel::class.java]
//    }

//    @Provides
//    fun providesTopHeadlinesViewModel(repository: NewsRepository): TopHeadlineViewModel {
//        return ViewModelProvider(activity, ViewModelFactory(TopHeadlineViewModel::class.java) {
//            TopHeadlineViewModel(repository, TopHeadlinesActivity.Filter.TOP_HEADLINES, "us")
//        })[TopHeadlineViewModel::class.java]
//    }

//    @Provides
//    fun providesSearchViewModel(repository: NewsRepository): SearchViewModel {
//        return ViewModelProvider(activity, ViewModelFactory(SearchViewModel::class.java){
//            SearchViewModel(repository)
//        })[SearchViewModel::class.java]
//    }
}