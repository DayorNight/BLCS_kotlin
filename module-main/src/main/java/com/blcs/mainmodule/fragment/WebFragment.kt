package com.blcs.mainmodule.fragment

import com.blcs.comlibs.base.BaseFragment
import com.blcs.mainmodule.R
import com.blcs.mainmodule.databinding.FragmentWebBinding

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

    override fun initUI(binding: FragmentWebBinding) {

    }

    override fun initData() {
    }
}