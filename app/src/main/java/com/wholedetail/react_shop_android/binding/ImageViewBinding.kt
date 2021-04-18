@file:Suppress("unused")

package com.wholedetail.react_shop_android.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("url")
fun ImageView.loadFromUrl(url: String?) {
    Glide.with(this).load(url).into(this)
}