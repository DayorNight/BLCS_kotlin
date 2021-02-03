package com.blcs.comlibs.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<viewBinding :ViewDataBinding > : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
}