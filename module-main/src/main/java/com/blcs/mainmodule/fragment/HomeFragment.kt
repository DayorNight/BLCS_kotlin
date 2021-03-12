package com.blcs.mainmodule.fragment

import android.os.Bundle
import com.blcs.comlibs.base.BaseFragment
import com.blcs.mainmodule.R
import com.blcs.mainmodule.databinding.FragmentHomeBinding

private const val ARG_PARAM1 = "pos"

/**
 * @des 首页片段
 */
class HomeFragment :BaseFragment<FragmentHomeBinding>() {

    companion object {
        @JvmStatic
        fun newInstance(pos: Int) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, pos)
                }
            }
    }

    private var pos: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            pos = it.getInt(ARG_PARAM1)
        }
    }

    override fun setLayoutId() = R.layout.fragment_home

    override fun initUI(binding: FragmentHomeBinding) {
        binding.location = when (pos) {
            0 -> {
                "引导1"
            }
            1 -> {
                "引导2"
            }
            2 -> {
                "引导3"
            }
            3 -> {
                "引导4"
            }
            else -> "引导1"
        }
    }

}