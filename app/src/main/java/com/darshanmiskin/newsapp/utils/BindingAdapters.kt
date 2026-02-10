package com.darshanmiskin.newsapp.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun imageUrl(imageView: AppCompatImageView, url: String?){
    Glide.with(imageView).load(url).into(imageView)
}