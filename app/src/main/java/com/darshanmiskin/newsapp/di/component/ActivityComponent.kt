package com.darshanmiskin.newsapp.di.component

import com.darshanmiskin.newsapp.di.ActivityScope
import com.darshanmiskin.newsapp.di.module.ActivityModule
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class], dependencies = [ApplicationComponent::class])
interface ActivityComponent {

}