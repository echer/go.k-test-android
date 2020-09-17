package com.alanecher.testegok.ui.main.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

object ImageLoader {

    @JvmStatic
    @BindingAdapter(value = ["loadImage"], requireAll = false)
    fun loadImage(imageView: ImageView, src: String?) {
        if (src != null) {
            Glide.with(imageView.context)
                .load(src)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        }
    }
}