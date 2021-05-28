package com.blcs.mainmodule.activity

import android.view.LayoutInflater.*
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.blcs.comlibs.base.BaseActivity
import com.blcs.comlibs.common.LinBottomNavigation
import com.blcs.mainmodule.R
import com.blcs.mainmodule.common.Const
import com.blcs.mainmodule.databinding.ActivityMainBinding
import com.blcs.mainmodule.fragment.CommonFragment
import com.blcs.mainmodule.fragment.HomeFragment
import com.blcs.mainmodule.fragment.MeFragment


@Route(path = Const.Activit_Main)
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun setFullScreen() = false

    override fun setLayoutId() = R.layout.activity_main

    override fun initUI(binding: ActivityMainBinding) {
        /* viewpager */
        binding.vpMain.apply {
            val homeFragment = HomeFragment.instance
            val commonFragment = CommonFragment.instance
            val meFragment = MeFragment.instance
            val fragments = arrayListOf(homeFragment, commonFragment, meFragment)
            /*viewpager*/
            adapter = object : FragmentPagerAdapter(supportFragmentManager,
                BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
                override fun getCount() = 3

                override fun getItem(position: Int) = fragments[position]
            }
            //滑动监听
            addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
                override fun onPageSelected(position: Int) {
                    LinBottomNavigation.setItem(binding.bnvMain, position)
                }
            })
        }

        /* 底部菜单栏 */
        binding.bnvMain.apply {
            //设置默认选中item
            LinBottomNavigation.setItem(binding.bnvMain, 0)
            //设置导航栏菜单项Item选中监听
            setOnNavigationItemSelectedListener { item ->
                val title = item.title.toString()
                when (item.itemId) {
                    R.id.item_home -> binding.vpMain.currentItem = 0
                    R.id.item_common -> binding.vpMain.currentItem = 1
                    R.id.item_me -> binding.vpMain.currentItem = 2
                }
                true
            }
            /*添加小红点*/
            LinBottomNavigation.addRedDot(this@MainActivity, binding.bnvMain, 1, 100)
        }
    }

}