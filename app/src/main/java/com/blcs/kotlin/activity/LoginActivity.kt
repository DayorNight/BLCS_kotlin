package com.blcs.kotlin.activity

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.blcs.comlibs.base.BaseActivity
import com.blcs.comlibs.common.LinArouter
import com.blcs.kotlin.R
import com.blcs.kotlin.common.Const
import com.blcs.kotlin.databinding.ActivityLoginBinding

/**
 * 登陆界面
 */
@Route(path = Const.Activit_Login)
class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    override fun setFullScreen() = true

    override fun setLayoutId() = R.layout.activity_login

    override fun initUI(binding: ActivityLoginBinding) {

    }

    fun onClickView(view : View){
        LinArouter.toActivity(Const.Activit_Main, this)
    }

}