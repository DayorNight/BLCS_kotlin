package com.blcs.mainmodule.activity

import android.graphics.Color
import android.view.LayoutInflater
import android.view.LayoutInflater.*
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.blcs.comlibs.base.BaseActivity
import com.blcs.comlibs.common.LinBottomNavigation
import com.blcs.mainmodule.R
import com.blcs.mainmodule.common.Const
import com.blcs.mainmodule.databinding.ActivityMainBinding
import com.blcs.mainmodule.fragment.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import org.w3c.dom.Text


@Route(path = Const.Activit_Main)
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun setLayoutId() = R.layout.activity_main

    override fun initUI(binding: ActivityMainBinding) {
        /*viewpager*/
        binding.vpMain.adapter = object : FragmentPagerAdapter(
            supportFragmentManager,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        ) {
            override fun getCount() = 4

            override fun getItem(position: Int) = HomeFragment.newInstance(position)
        }

        //滑动监听
        binding.vpMain.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                LinBottomNavigation.setItem(binding.bnvMain, position)
            }
        })
        //设置默认选中item
        LinBottomNavigation.setItem(binding.bnvMain, 0)
        //设置导航栏菜单项Item选中监听
        binding.bnvMain.setOnNavigationItemSelectedListener { item ->
            val title = item.title.toString()
            when (item.itemId) {
                R.id.item_home -> binding.vpMain.currentItem = 0
                R.id.item_common -> binding.vpMain.currentItem = 1
                R.id.item_me -> binding.vpMain.currentItem = 2
            }
            true
        }

        /*添加小红点*/
        LinBottomNavigation.addRedDot(this,binding.bnvMain,1,100)

    }

}