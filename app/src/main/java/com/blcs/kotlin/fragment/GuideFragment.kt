package com.blcs.kotlin.fragment

import android.os.Bundle
import android.view.View
import com.blcs.comlibs.base.BaseFragment
import com.blcs.comlibs.common.Lg
import com.blcs.kotlin.R
import com.blcs.kotlin.databinding.FragmentGuideBinding

private const val ARG_PARAM1 = "pos"

/**
 * 引导页
 */
class GuideFragment : BaseFragment<FragmentGuideBinding>() {
    companion object {
        @JvmStatic
        fun newInstance(pos: Int) =
            GuideFragment().apply {
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

    override fun setLayoutId() = R.layout.fragment_guide

    override fun initUI(binding: FragmentGuideBinding) {
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