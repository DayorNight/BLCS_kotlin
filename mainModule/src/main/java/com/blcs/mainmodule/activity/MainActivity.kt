package com.blcs.mainmodule.activity

import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.alibaba.android.arouter.facade.annotation.Route
import com.blcs.comlibs.base.BaseActivity
import com.blcs.comlibs.common.LinArouter
import com.blcs.mainmodule.R
import com.blcs.mainmodule.adapter.SimpleListAdapter
import com.blcs.mainmodule.common.Const
import com.blcs.mainmodule.databinding.ActivityMainBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener

@Route(path = Const.Activit_Main)
class MainActivity : BaseActivity<ActivityMainBinding>(), OnItemClickListener {

    override fun setLayoutId() = R.layout.activity_main
    private val arrayListOf =
        mutableListOf<Any>("数据绑定", "Databind2", "Databind3", "Databind4", "Databind5")

    override fun initUI(binding: ActivityMainBinding) {
        binding.rv.layoutManager = LinearLayoutManager(this)
        val adapter = SimpleListAdapter(arrayListOf)
        adapter.setOnItemClickListener(this)
        binding.rv.adapter = adapter
        binding.rv.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
    }

    /**
     * recyclerView 点击事件
     */
    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        when (position) {
            0 -> {
                LinArouter.toActivity(Const.Activit_Databinding)
            }
        }
    }
}