package com.darshanmiskin.newsapp.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory<T : ViewModel>(private val clazz: Class<T>, private val creator: () -> T) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(clazz)) return creator() as T
        else throw IllegalArgumentException("Unknown class name!")
    }
}