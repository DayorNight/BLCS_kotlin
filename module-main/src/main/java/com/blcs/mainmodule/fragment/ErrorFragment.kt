package com.blcs.mainmodule.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.blcs.comlibs.base.BaseFragment
import com.blcs.comlibs.common.Lg
import com.blcs.comlibs.common.LinCommon
import com.blcs.comlibs.common.LinQrCode
import com.blcs.comlibs.common.toast
import com.blcs.mainmodule.R
import com.blcs.mainmodule.databinding.FragmentErrorBinding
import com.blcs.mainmodule.databinding.FragmentQrCodeBinding
import com.yzq.zxinglibrary.common.Constant

/**
 * 报错页面
 */
class ErrorFragment : BaseFragment<FragmentErrorBinding>() {

    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ErrorFragment()
        }
    }
    override fun setLayoutId() = R.layout.fragment_error

    override fun initUI(bindView: FragmentErrorBinding) {

    }

    override fun initData() {
    }
}