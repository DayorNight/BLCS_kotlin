package com.blcs.mainmodule.fragment

import com.blcs.comlibs.base.BaseFragment
import com.blcs.mainmodule.R
import com.blcs.mainmodule.databinding.FragmentErrorBinding

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

    override fun initUI(binding: FragmentErrorBinding) {

    }

    override fun initData() {
    }
}