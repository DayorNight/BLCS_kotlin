package com.blcs.comlibs.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.blcs.comlibs.R
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.ktx.immersionBar

abstract class BaseActivity<viewBinding : ViewDataBinding> : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*沉浸式状态栏*/
        immersionBar {
            if (setFullScreen()){
                transparentStatusBar()
            }else{
                statusBarColor(R.color.colorPrimary)
            }
            autoDarkModeEnable(true)
        }
        /* 绑定布局 */
        val binding = DataBindingUtil.setContentView<viewBinding>(
            this,
            setLayoutId()
        )
        initUI(binding)
    }

    /**
     * 定义布局
     */
    abstract fun setLayoutId():Int

    /**
     * 定义UI
     */
    abstract fun initUI(binding: viewBinding)

    /**
     * 设置是否全屏
     */
    open fun setFullScreen() = false

}