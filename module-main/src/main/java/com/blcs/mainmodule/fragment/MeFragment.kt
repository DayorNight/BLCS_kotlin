package com.blcs.mainmodule.fragment

import android.os.Bundle
import com.blcs.comlibs.base.BaseFragment
import com.blcs.mainmodule.R
import com.blcs.mainmodule.databinding.FragmentCommonBinding
import com.blcs.mainmodule.databinding.FragmentHomeBinding
import com.blcs.mainmodule.databinding.FragmentMeBinding

private const val ARG_PARAM1 = "pos"

/**
 * @des 公共片段
 */
class MeFragment :BaseFragment<FragmentMeBinding>() {

    companion object {
        @JvmStatic
        fun newInstance(pos: Int) =
            MeFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, pos)
                }
            }
    }

    private var pos: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            pos = getInt(ARG_PARAM1)
        }
    }

    override fun setLayoutId() = R.layout.fragment_me

    override fun initUI(binding: FragmentMeBinding) {

    }

}