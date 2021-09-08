package com.blcs.mainmodule.fragment

import com.blcs.comlibs.base.BaseFragment
import com.blcs.mainmodule.R
import com.blcs.mainmodule.databinding.FragmentCommonBinding


/**
 * @des 公共片段
 */
class CommonFragment private constructor(): BaseFragment<FragmentCommonBinding>() {

    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            CommonFragment()
        }
    }


    override fun setLayoutId() = R.layout.fragment_common


    override fun initUI(binding: FragmentCommonBinding) {
        binding.location = "广场"
    }

    override fun initData() {

    }

}