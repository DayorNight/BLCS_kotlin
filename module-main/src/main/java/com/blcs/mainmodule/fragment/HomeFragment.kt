package com.blcs.mainmodule.fragment

import android.os.Bundle
import android.view.Gravity
import androidx.recyclerview.widget.LinearLayoutManager
import com.blcs.comlibs.base.BaseFragment
import com.blcs.comlibs.common.Lg
import com.blcs.comlibs.common.toActivity
import com.blcs.comlibs.common.toFragment
import com.blcs.comlibs.common.toast
import com.blcs.mainmodule.R
import com.blcs.mainmodule.adapter.SimpleListAdapter
import com.blcs.mainmodule.common.Const
import com.blcs.mainmodule.databinding.FragmentHomeBinding



/**
 * @des 首页片段
 */
class HomeFragment :BaseFragment<FragmentHomeBinding>() {

    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            HomeFragment()
        }
    }

    private var pos: Int? = null


    override fun setLayoutId() = R.layout.fragment_home

    override fun initUI(bindView: FragmentHomeBinding) {
        bindView.rvCommon.apply {
            layoutManager = LinearLayoutManager(activity)
            val array = resources.getStringArray(R.array.Utils)
            Lg.e("====array "+array.size)
            val mAdapter = SimpleListAdapter(array.toMutableList(), Gravity.LEFT)
            adapter = mAdapter
            mAdapter.setOnItemClickListener { adapter, view, position ->
                when(position){
                    0 -> toFragment(Const.Fragment_QR)
                    1 -> toFragment(Const.Fragment_TURNTABLE)
                    else -> adapter.data[position]?.toast(activity)
                }
            }
        }
    }

}