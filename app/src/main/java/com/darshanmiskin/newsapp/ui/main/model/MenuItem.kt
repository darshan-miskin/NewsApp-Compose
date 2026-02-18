package com.darshanmiskin.newsapp.ui.main.model

import androidx.annotation.StringRes

data class MenuItem(@param:StringRes val title: Int, val listener: () -> Unit)