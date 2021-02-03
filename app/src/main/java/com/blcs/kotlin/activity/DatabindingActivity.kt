package com.blcs.kotlin.activity

import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.blcs.comlibs.base.BaseActivity
import com.blcs.comlibs.common.Lg
import com.blcs.kotlin.R
import com.blcs.kotlin.bean.User
import com.blcs.kotlin.bean.User3
import com.blcs.kotlin.databinding.ActivityDatabindingBinding

class DatabindingActivity : BaseActivity<ActivityDatabindingBinding>() {

    override fun setLayoutId() = R.layout.activity_databinding
    var user: User? = null
    val user3 = User3()
    override fun initUI(binding: ActivityDatabindingBinding) {

        user = User("第一名字", "User", null)

        user3.firstName = "xxxx"
        user3.lastName = "xsssx"
        binding.user = user3
    }

    /**
     * 点击事件
     */
    fun onClickView(view: View) {
        user3.firstName = "的地方地方"
        Toast.makeText(this, "第一名字", Toast.LENGTH_SHORT).show()
        Lg.e("第一名字")
    }



}