package com.blcs.mainmodule.activity

import android.content.Intent
import android.graphics.Color
import android.text.TextUtils
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blcs.comlibs.base.BaseActivity
import com.blcs.comlibs.common.ConstKey
import com.blcs.comlibs.common.Lg
import com.blcs.mainmodule.R
import com.blcs.mainmodule.common.Const
import com.blcs.mainmodule.databinding.ActivityPublicBinding
import com.blcs.mainmodule.fragment.*


@Route(path = Const.Activit_Public)
class PublicActivity : BaseActivity<ActivityPublicBinding>() {

    @Autowired
    @JvmField
    var where = ""

    override fun setLayoutId() = R.layout.activity_public
    var toolbar :Toolbar? =null
    override fun initUI(bindView: ActivityPublicBinding) {
        ARouter.getInstance().inject(this)
         toolbar = bindView?.tlToolbar
        //设置导航栏图标
        bindView?.tlToolbar.run {
            navigationIcon = ContextCompat.getDrawable(
                this@PublicActivity,
                R.drawable.ic_back
            )
            setTitleTextColor(Color.WHITE)
            contentInsetStartWithNavigation = 0
            setNavigationOnClickListener {
                mrFragment.back()
            }
            setBackgroundColor(ContextCompat.getColor(this@PublicActivity, R.color.colorPrimary))
//            toolbar.inflateMenu(R.menu.menu_sss)
//            tlToolbar.setOnMenuItemClickListener { item ->
//                when (item?.itemId) {
//                    R.id.action_1 -> Toast.makeText(activity, "action_1", Toast.LENGTH_SHORT)
//                        .show()
//                    R.id.action_2 -> Toast.makeText(activity, "action_2", Toast.LENGTH_SHORT)
//                        .show()
//                    R.id.action_3 -> Toast.makeText(activity, "action_3", Toast.LENGTH_SHORT)
//                        .show()
//                    R.id.action_4 -> Toast.makeText(activity, "action_4", Toast.LENGTH_SHORT)
//                        .show()
//                }
//                true
//            }
        }

        bindView?.flContent.apply {
            Lg.e("===== $where")
            toolbar?.title = where
            if (TextUtils.isEmpty(where))
                finish()
            else
                mrFragment.addFrament(R.id.fl_content, where, intent.extras, true, getFrament(where))

        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val where = intent?.getStringExtra(ConstKey.WHERE)
        Lg.e("========$where")
        toolbar?.title = where
        mrFragment.addFrament(R.id.fl_content, where, intent?.extras, true, getFrament(where))
    }

    /**
     * 获取对应的Fragment
     */
    private fun getFrament(alias: String?) =
        when (alias) {
            Const.Fragment_QR -> QRcodeFragment.instance
            Const.Fragment_WEB -> WebFragment.instance
            Const.Fragment_TURNTABLE -> TurnTableFragment.instance
            Const.Fragment_ANDROID -> AndroidFragment.instance
            else -> ErrorFragment.instance
        }

}