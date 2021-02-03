package com.blcs.kotlin.activity

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.blcs.comlibs.base.BaseActivity
import com.blcs.kotlin.R
import com.blcs.kotlin.adapter.SimpleListAdapter
import com.blcs.kotlin.databinding.ActivitySplashBinding
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener

class SplashActivity : BaseActivity<ActivitySplashBinding>(), OnItemClickListener {
    private val arrayListOf = mutableListOf<Any>("数据绑定", "Databind2", "Databind3", "Databind4", "Databind5")

    override fun setLayoutId() = R.layout.activity_splash

    override fun initUI(binding: ActivitySplashBinding) {
        binding.rv.layoutManager = LinearLayoutManager(this)
        val adapter = SimpleListAdapter(arrayListOf)
        adapter.setOnItemClickListener(this)
        binding.rv.adapter = adapter
        binding.rv.addItemDecoration( DividerItemDecoration(this,OrientationHelper.VERTICAL))
    }

    /**
     * recyclerView 点击事件
     */
    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        when(position){
            0 ->{ startActivity(Intent(this, DatabindingActivity::class.java)) }
        }
    }


}

