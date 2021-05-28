package com.blcs.mainmodule.activity

import android.text.TextUtils
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.blcs.comlibs.base.BaseActivity
import com.blcs.comlibs.common.Lg
import com.blcs.mainmodule.R
import com.blcs.mainmodule.common.Const
import com.blcs.mainmodule.databinding.ActivityPublicBinding
import com.blcs.mainmodule.fragment.ErrorFragment
import com.blcs.mainmodule.fragment.QRcodeFragment
import com.blcs.mainmodule.fragment.TurnTableFragment
import com.blcs.mainmodule.fragment.WebFragment


@Route(path = Const.Activit_Public)
class PublicActivity : BaseActivity<ActivityPublicBinding>() {

    @Autowired
    @JvmField
    var where = ""

    override fun setLayoutId() = R.layout.activity_public

    override fun initUI(bindView: ActivityPublicBinding) {
        ARouter.getInstance().inject(this)

        //设置导航栏图标
        bindView?.tlToolbar.run {
            navigationIcon = ContextCompat.getDrawable(
                this@PublicActivity,
                R.drawable.ic_back
            )
            title = where
            setTitleTextAppearance(this@PublicActivity, R.style.Toolbar_Title)
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
            if (TextUtils.isEmpty(where))
                finish()
            else
                mrFragment.addFrament(R.id.fl_content, where, intent.extras, true, getFrament(where))

        }

    }

    /**
     * 获取对应的Fragment
     */
    private fun getFrament(alias: String?) =
        when (alias) {
            Const.Fragment_QR -> QRcodeFragment.instance
            Const.Fragment_WEB -> WebFragment.instance
            Const.Fragment_TURNTABLE -> TurnTableFragment.instance
            else -> ErrorFragment.instance
        }

}