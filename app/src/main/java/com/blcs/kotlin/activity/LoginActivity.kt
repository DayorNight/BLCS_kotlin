package com.blcs.kotlin.activity

import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.blcs.comlibs.base.BaseActivity
import com.blcs.comlibs.common.toActivity
import com.blcs.comlibs.common.toast
import com.blcs.comlibs.interfaces.IClickListener
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
        binding.rbAgreement.apply {
            text = SpannableStringBuilder().apply {
                append("同意")
                append(clickContent("《XXX》",object :IClickListener{
                    override fun clickCall() {
                    }
                }))
                append("和")
                append(clickContent("《SSS》",object :IClickListener{
                    override fun clickCall() {
                    }
                }))
                append("协议")
            }
            movementMethod = LinkMovementMethod.getInstance()
        }

    }

    private fun clickContent(content :String,listener: IClickListener) =
        SpannableString(content).apply {
            val clickXXX = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    content.toast(this@LoginActivity)
                    listener.clickCall()
                }

                override fun updateDrawState(@NonNull ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = ContextCompat.getColor(
                        this@LoginActivity,
                        R.color.colorPrimary
                    )
                    ds.isUnderlineText = false
                }
            }
            setSpan(clickXXX, 0, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }


    /**
     * 一键登录
     */
    fun onClickOauth(view: View){
        toActivity(Const.Activit_Main)
        finish()
    }

    /**
     * 其它方式登陆
     */
    fun onClickOtherWays(view: View) {
        defaultToast()
    }

    /**
     * 微信
     */
    fun onClickWXLogin(view: View) {
        defaultToast()
    }
    /**
     * QQ
     */
    fun onClickQQLogin(view: View) {
        defaultToast()
    }

    /**
     * 微博
     */
    fun onClickWBLogin(view: View) {
        defaultToast()
    }

}