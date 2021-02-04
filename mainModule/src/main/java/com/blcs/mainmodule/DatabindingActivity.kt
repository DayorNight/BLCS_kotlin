package com.blcs.mainmodule

import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.blcs.comlibs.base.BaseActivity
import com.blcs.comlibs.common.Lg
import com.blcs.mainmodule.bean.User
import com.blcs.mainmodule.common.Const
import com.blcs.mainmodule.databinding.ActivityDatabindingBinding
import com.blcs.mainmodule.viewmodel.DatabindingViewModel

@Route(path = Const.Activit_Databinding)
class DatabindingActivity : BaseActivity<ActivityDatabindingBinding>() {

    override fun setLayoutId() = R.layout.activity_databinding

    private val viewModel: DatabindingViewModel by viewModels()
    var user: User? = null
    override fun initUI(binding: ActivityDatabindingBinding) {

        user = User("第一名字", "User", null)

        binding.user = user
        // user 数据该变 通知UI更新
        viewModel.user.observe(this,
            Observer {
                //update ui
            })
    }

    /**
     * 点击事件
     */
    fun onClickView(view: View) {
        user?.firstName = "的地方地方"
        Toast.makeText(this, "第一名字" + view.id, Toast.LENGTH_SHORT).show()
        Lg.e("第一名字")
    }


}