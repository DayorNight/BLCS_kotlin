package com.blcs.mainmodule.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.text.TextUtils
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.blcs.comlibs.base.BaseFragment
import com.blcs.comlibs.common.*
import com.blcs.mainmodule.R
import com.blcs.mainmodule.common.Const
import com.blcs.mainmodule.databinding.FragmentQrCodeBinding
import com.blcs.mainmodule.databinding.FragmentWebBinding
import com.yzq.zxinglibrary.common.Constant

/**
 * 二维码页面
 */
class WebFragment : BaseFragment<FragmentWebBinding>(){

    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            WebFragment()
        }
    }

    override fun setLayoutId() = R.layout.fragment_web

    override fun initUI(bindView: FragmentWebBinding) {

    }
}