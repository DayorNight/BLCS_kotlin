package com.blcs.mainmodule.fragment

import android.graphics.Color
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.blcs.comlibs.base.BaseFragment
import com.blcs.comlibs.common.Lg
import com.blcs.comlibs.common.LinRecycler
import com.blcs.comlibs.common.toFragment
import com.blcs.comlibs.common.toast
import com.blcs.comlibs.widget.dialogFragment.LinCustomDialogFragment

import com.blcs.mainmodule.R
import com.blcs.mainmodule.adapter.SimpleListAdapter
import com.blcs.mainmodule.common.Const
import com.blcs.mainmodule.databinding.FragmentMeBinding

/**
 * @des 我的页面
 */
class MeFragment :BaseFragment<FragmentMeBinding>() {

    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            MeFragment()
        }
    }

    override fun setLayoutId() = R.layout.fragment_me
    private var comDialog: LinCustomDialogFragment? = null

    override fun initUI(bindView: FragmentMeBinding) {
        bindView.apply {
            collapsingToolbar.title = "登陆"
            collapsingToolbar.setExpandedTitleColor(Color.WHITE)
            collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE)
            val appCompatActivity = activity as AppCompatActivity
            appCompatActivity?.setSupportActionBar(bindView.toolbar)
            fbButton.setOnClickListener {
                Lg.e("======登陆=======")
            }
        }
        val array = resources.getStringArray(R.array.Other)
        val mAdapter = SimpleListAdapter(array.toMutableList(), Gravity.LEFT)
        LinRecycler.init(activity, mAdapter, bindView.rvToDoList)
        mAdapter.setOnItemClickListener { adapter, view, position ->
            when(position){
                0 -> toFragment(Const.Fragment_ANDROID)
                else -> adapter.data[position]?.toast(activity)
            }
        }
    }

    override fun initData() {

    }

}