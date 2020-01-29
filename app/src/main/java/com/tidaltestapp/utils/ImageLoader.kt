package com.tidaltestapp.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.tidaltestapp.R

/**
 * Created by naderbaltaji on 2020-01-29
 */
class ImageLoader(val context: Context) {

    fun loadImage(url: String?, imageView: ImageView) {
        Glide
            .with(context)
            .load(url)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.shape_rectangluar_gray_background)
                    .centerCrop()
            )
            .into(imageView)
    }

    fun loadCircularImage(url: String?, imageView: ImageView) {
        Glide
            .with(context)
            .load(url)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.shape_circular_gray_background)
                    .circleCrop()
            )
            .into(imageView)
    }
}