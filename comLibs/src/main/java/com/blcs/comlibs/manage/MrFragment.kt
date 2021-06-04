package com.blcs.comlibs.manage

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.blcs.comlibs.R
import com.blcs.comlibs.common.Lg
import com.blcs.comlibs.common.LinApp

/**
 * 片段管理
 * @author linweibin
 * @date   2021/5/25
 */
class MrFragment {
    private val fragments = mutableListOf<Fragment>()
    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            MrFragment()
        }
    }
    private var fm : FragmentManager? = null
    private var activity : FragmentActivity? = null
    fun get(activity: FragmentActivity): MrFragment{
        this.activity = activity
        fm = activity.supportFragmentManager
        return this
    }

    private fun popBackStack() {
        if (fragments.size > 1) {
            // 从后退栈中弹出
            fm?.popBackStack()
            fragments.removeAt(fragments.size - 1)
        }
    }

    /**
     * 返回
     * @des 如果剩下 最后一个片段就销毁Activity,否则执行后退栈
     */
    fun back() {
        Lg.e("==========数量： ${MrActivity.instance.getActivitySize()}")
        if (fragments.size <= 1) {
            clearAllFrament()
            activity?.finish()
        } else {
            popBackStack()
        }
    }

    /**
     * 添加片段
     * @param viewId   Frament 放置的FramentLayout的id
     * @param activity 对应的Activity
     * @param alias    别名，用于管理Fragment的名字
     * @param bundle   传递的参数，如果不传，设置null即可
     * @param isAnim   是否添加动画
     */
    fun addFrament(viewId: Int, alias: String?, bundle: Bundle?, isAnim: Boolean,base: Fragment) {
        fragments.add(base)
        fm?.apply {
            val bt = beginTransaction()
            if (isAnim){
                bt.setCustomAnimations(
                    R.anim.right_push_in, R.anim.left_push_out,
                    R.anim.left_push_in, R.anim.right_push_out
                )
            }
            base?.arguments = bundle
            bt?.add(viewId, base, alias)
                ?.addToBackStack(null)
                ?.commitAllowingStateLoss()
        }
    }

    /**
     * 替换Frament 这里先删除再添加，完成替换操作
     * @param viewId   Frament 放置的FramentLayout
     * @param alias    别名
     */
    private fun replaceFrament(viewId: Int, alias: String,base: Fragment) {
        popBackStack()
        fragments.add(base)
        fm?.beginTransaction()
            ?.add(viewId, base, alias)
            ?.addToBackStack(null)
            ?.commit()
    }

    /**
     * 获取上一个片段
     */
    private fun getLastFrament(): Fragment? =
        if (fragments.size - 1 >= 0)
            fragments[fragments.size - 1]
        else null



    /**
     * 清除当前Activity的所有片段 Fragment
     */
    private fun clearAllFrament() {
        for (i in fragments.indices) {
            popBackStack()
        }
        fragments.clear()
    }
}