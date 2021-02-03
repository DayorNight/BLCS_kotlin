package com.blcs.comlibs.adapter

import android.graphics.drawable.ColorDrawable
import androidx.databinding.BindingConversion


@BindingConversion
fun convertColorToDrawable(color: Int) = ColorDrawable(color)