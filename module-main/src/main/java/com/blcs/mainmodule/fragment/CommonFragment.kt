package com.blcs.mainmodule.fragment

import android.os.Bundle
import com.blcs.comlibs.base.BaseFragment
import com.blcs.mainmodule.R
import com.blcs.mainmodule.databinding.FragmentCommonBinding
import com.blcs.mainmodule.databinding.FragmentHomeBinding


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


    override fun initUI(bindView: FragmentCommonBinding) {
        bindView. location = "广场"
    }

}