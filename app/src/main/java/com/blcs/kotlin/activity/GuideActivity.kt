package com.blcs.kotlin.activity

import android.graphics.Color
import android.view.View
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.blcs.comlibs.base.BaseActivity
import com.blcs.comlibs.common.LinArouter
import com.blcs.kotlin.R
import com.blcs.kotlin.common.Const
import com.blcs.kotlin.databinding.ActivityGuideBinding
import com.blcs.kotlin.fragment.GuideFragment
import net.lucode.hackware.magicindicator.ViewPagerHelper
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator.GONE
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator.OnCircleClickListener
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator

/**
 * 引导页
 */
@Route(path = Const.Activit_Guide)
class GuideActivity : BaseActivity<ActivityGuideBinding>() {

    override fun setLayoutId() = R.layout.activity_guide
    override fun initUI(binding: ActivityGuideBinding) {

        /*viewpager 指示器*/
        val circleNavigator = CircleNavigator(this)
        circleNavigator.circleCount = 4
        circleNavigator.circleColor = Color.RED
        circleNavigator.circleClickListener =
            OnCircleClickListener { index ->
                binding.viewpager.currentItem = index

            }
        binding.magicIndicator.navigator = circleNavigator
        /*viewpager*/
        binding.viewpager.adapter = object : FragmentPagerAdapter(
            supportFragmentManager,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        ) {
            override fun getCount() = 4

            override fun getItem(position: Int) = GuideFragment.newInstance(position)
        }
        binding.viewpager.addOnPageChangeListener(object :ViewPager.SimpleOnPageChangeListener(){
            override fun onPageSelected(position: Int) {
                binding.magicIndicator.visibility = if (position == 3) View.GONE else View.VISIBLE
                binding.btnGo.visibility = if (position == 3) View.VISIBLE else View.GONE
            }
        })
        /*绑定指示器*/
        ViewPagerHelper.bind(binding.magicIndicator, binding.viewpager)
    }

    fun onClickView(view : View){
        LinArouter.toActivity(Const.Activit_Login, this)
    }
}