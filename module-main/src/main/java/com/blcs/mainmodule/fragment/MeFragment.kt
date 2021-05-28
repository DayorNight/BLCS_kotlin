package com.blcs.mainmodule.fragment

import com.blcs.comlibs.base.BaseFragment
import com.blcs.mainmodule.R
import com.blcs.mainmodule.databinding.FragmentCommonBinding
import com.blcs.mainmodule.databinding.FragmentHomeBinding
import com.blcs.mainmodule.databinding.FragmentMeBinding

/**
 * @des 公共片段
 */
class MeFragment :BaseFragment<FragmentMeBinding>() {

    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            MeFragment()
        }
    }

    override fun setLayoutId() = R.layout.fragment_me

    override fun initUI(bindView: FragmentMeBinding) {
        bindView.location = "我的"
    }

}