package com.onirutla.metalgearcharacter.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("loadImageFromId")
fun loadImageFromId(view: ImageView, id: Int?) {
    if (id != null) {
        GlideApp.with(view.context).load(id).into(view)
    }
}