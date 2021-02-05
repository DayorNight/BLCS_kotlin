package com.blcs.kotlin.activity

import com.alibaba.android.arouter.facade.annotation.Route
import com.blcs.comlibs.base.BaseActivity
import com.blcs.kotlin.R
import com.blcs.kotlin.common.Const
import com.blcs.kotlin.databinding.ActivityGuideBinding

@Route(path = Const.Activit_Guide)
class GuideActivity :BaseActivity<ActivityGuideBinding>(){
    override fun setLayoutId() = R.layout.activity_guide

    override fun initUI(binding: ActivityGuideBinding) {

    }
}