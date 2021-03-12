package com.blcs.comlibs.common

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import com.blcs.comlibs.R
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.reflect.Field

object LinBottomNavigation {
    private val mutableMapOf = mutableMapOf<Int, TextView>()
    /**
     * BottomNavigationView去除动画
     * @param view
     */
    @SuppressLint("RestrictedApi")
    fun disableShiftMode(view: BottomNavigationView) {
        val menuView: BottomNavigationMenuView = view.getChildAt(0) as BottomNavigationMenuView
        try {
            val shiftingMode: Field = menuView::class.java.getDeclaredField("mShiftingMode")
            shiftingMode.isAccessible = true
            shiftingMode.setBoolean(menuView, false)
            shiftingMode.isAccessible = false
            for (i in 0 until menuView.childCount) {
                val item: BottomNavigationItemView =
                    menuView.getChildAt(i) as BottomNavigationItemView
                //去除动画
//                item.setShiftingMode(false); //api 28之前
                item.setChecked(item.getItemData().isChecked())
            }
        } catch (e: NoSuchFieldException) {
            Lg.e("Unable to get shift mode field")
        } catch (e: IllegalAccessException) {
            Lg.e("Unable to change value of shift mode")
        }
    }

    /**
     * 指定初始Item
     * @param view
     * @param pos
     */
    fun setItem(view: BottomNavigationView, pos: Int) {
        view.menu.getItem(pos).isChecked = true
    }

    /**
     * 是否开启动画
     * @param view
     */
    fun openAnimation(view: BottomNavigationView, isAnimation: Boolean) {
        view.labelVisibilityMode = if (isAnimation) 0 else 1
    }

    /**
     * 添加Item
     * @param view
     */
    fun addItem(
        view: BottomNavigationView,
        groupID: Int,
        itemID: Int,
        orderID: Int,
        text: String?
    ) {
        view.menu.add(groupID, itemID, orderID, text)
    }

    /**
     * 删除Item
     * @param view
     */
    fun remove(view: BottomNavigationView, pos: Int) {
        view.menu.removeItem(pos)
    }


    /**
     * 添加消息红点
     * @param pos 要添加红点的menu位置
     * @param msgNum 消息数量
     */
    fun addRedDot(context : Context, view: BottomNavigationView, pos: Int, msgNum: Int):TextView? {
        var tvDot:TextView? = null
        if(mutableMapOf.containsKey(pos)){
            tvDot = mutableMapOf[pos]
        }else{
            val menuView = view.getChildAt(0) as BottomNavigationMenuView
            val itemView = menuView.getChildAt(pos) as BottomNavigationItemView
            val view = LayoutInflater.from(context).inflate(
                R.layout.include_tv,
                menuView,false
            )
            tvDot = view.findViewById<TextView>(R.id.tv_mark)
            tvDot.text = if(msgNum>99) "99+" else msgNum.toString()
            itemView.addView(view)
            mutableMapOf[pos] = tvDot
        }
        return tvDot
    }
}