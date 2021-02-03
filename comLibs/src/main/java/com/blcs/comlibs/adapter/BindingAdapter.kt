package com.blcs.comlibs.adapter

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter


/**
 * @Des 绑定适配器
 * 设置 paddingLeft
 */
@BindingAdapter("android:paddingLeft")
fun setPaddingLeft(view: View, padding: Int) {
    view.setPadding(
        padding,
        view.paddingTop,
        view.paddingRight,
        view.paddingBottom
    )
}

/**
 * 加载Image
 * @param
 * <ImageView app:imageUrl="@{venue.imageUrl}" app:error="@{@drawable/venueError}" />
 */
@BindingAdapter("imageUrl", "error")
fun loadImage(view: ImageView, url: String, error: Drawable) {
//    Picasso.get().load(url).error(error).into(view)
}

/**
 * 双向绑定  InverseBindingAdapter
 */
@InverseBindingAdapter(attribute = "time")
fun getTime(view: View) : Int {
    return view.id
}