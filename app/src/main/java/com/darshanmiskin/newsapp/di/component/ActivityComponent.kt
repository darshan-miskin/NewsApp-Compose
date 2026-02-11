package com.darshanmiskin.newsapp.di.component

import com.darshanmiskin.newsapp.di.ActivityScope
import com.darshanmiskin.newsapp.di.module.ActivityModule
import com.darshanmiskin.newsapp.ui.countries.CountriesActivity
import com.darshanmiskin.newsapp.ui.languages.LanguagesActivity
import com.darshanmiskin.newsapp.ui.newssources.NewsSourceActivity
import com.darshanmiskin.newsapp.ui.search.SearchActivity
import com.darshanmiskin.newsapp.ui.topheadlines.TopHeadlinesActivity
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [ApplicationComponent::class])
interface ActivityComponent {
    fun inject(activity: CountriesActivity)
    fun inject(activity: LanguagesActivity)
    fun inject(activity: NewsSourceActivity)
    fun inject(activity: TopHeadlinesActivity)
    fun inject(activity: SearchActivity)
}