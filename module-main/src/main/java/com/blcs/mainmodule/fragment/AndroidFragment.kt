package com.blcs.mainmodule.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.blcs.comlibs.base.BaseFragment
import com.blcs.comlibs.common.LinRecycler
import com.blcs.mainmodule.R
import com.blcs.mainmodule.adapter.AndroidAdapter
import com.blcs.mainmodule.databinding.FragmentAndroidBinding


class AndroidFragment : BaseFragment<FragmentAndroidBinding>() {

    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            AndroidFragment()
        }
    }

    override fun setLayoutId() = R.layout.fragment_android
    private var androidAdapter: AndroidAdapter? = null
    override fun initUI(binding: FragmentAndroidBinding) {
        binding.rvViewpager.apply {
            androidAdapter = AndroidAdapter(parentFragmentManager)
            LinRecycler.init(
                activity,
                LinearLayoutManager.HORIZONTAL,
                androidAdapter!!,
                this
            )
            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(this)
            setOnTouchListener { _, _ -> false }
        }
    }

    override fun initData() {
        androidAdapter?.apply {
            checkArticle(0,-1)
            notifyDataSetChanged()
        }
    }
}