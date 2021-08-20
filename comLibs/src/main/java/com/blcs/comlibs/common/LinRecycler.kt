package com.blcs.comlibs.common

import android.content.Context
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.blcs.comlibs.widget.DividerItem
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * @author linweibin
 * @date   2021/7/26
 */
object LinRecycler {
    /**
     * 初始化配置
     */
    fun init(
        context: Context?,
        mAdapter: Adapter<BaseViewHolder>,
        mRecycyler: RecyclerView
    ) {
        init(context, LinearLayoutManager.VERTICAL, mAdapter, mRecycyler, true)
    }
    /**
     * 初始化配置
     */
    fun init(
        context: Context?,
        orientation: Int,
        mAdapter: Adapter<BaseViewHolder>,
        mRecycyler: RecyclerView
    ) {
        init(context, orientation, mAdapter, mRecycyler, true)
    }

    /**
     * 初始化配置
     */
    fun init(
        context: Context?,
        orientation: Int,
        mAdapter: Adapter<BaseViewHolder>,
        mRecycyler: RecyclerView,
        showDecoration: Boolean
    ) {
        mRecycyler.layoutManager = LinearLayoutManager(context,orientation,false)

        mRecycyler.adapter = mAdapter
        if (showDecoration) {
            mRecycyler.addItemDecoration(DividerItem(context, orientation))
        }
    }

    /**
     * 从控件所在位置移动到控件的底部
     * @return
     */
    fun moveToViewBottom(): TranslateAnimation? {
        val mHiddenAction = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
            0.0f, Animation.RELATIVE_TO_SELF, 1.0f
        )
        mHiddenAction.duration = 500
        return mHiddenAction
    }

    /**
     * 从控件的底部移动到控件所在位置
     *
     * @return
     */
    fun moveToViewLocation(): TranslateAnimation? {
        val mHiddenAction = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
            1.0f, Animation.RELATIVE_TO_SELF, 0.0f
        )
        mHiddenAction.duration = 500
        return mHiddenAction
    }
}