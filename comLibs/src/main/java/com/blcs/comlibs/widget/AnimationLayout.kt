package com.blcs.comlibs.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import com.blcs.comlibs.widget.LikeAnim.CurveEvaluatorRecord
import java.util.*

/**
 * @ClassName: AnimationLayout
 * @Author: KaiSenGao
 * @CreateDate: 2020/12/24 10:58
 * @Description:
 */
abstract class AnimationLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs), IAnimationLayout {
    protected val mRandom = Random()
    protected var mViewWidth = 0
    protected var mViewHeight = 0
    protected var mPicWidth = 0f
    protected var mPicHeight = 0f
    protected var mAnimatorSets: MutableList<AnimatorSet>? = null
    protected var mEvaluatorRecord: CurveEvaluatorRecord? = null

    /**
     * 初始化
     */
    protected open fun init() {
        // 动画集合
        mAnimatorSets = ArrayList()
        // 贝塞尔曲线缓存类
        mEvaluatorRecord = CurveEvaluatorRecord()
    }

    /**
     * 获取资源图片信息
     *
     * @param resId 资源Id
     */
    fun getPictureInfo(@DrawableRes resId: Int) {
        // 获取图片
        val options = BitmapFactory.Options()
        // 只读取图片，不加载到内存中
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(context.resources, resId, options)
        // 获取图片的宽高
        mPicWidth = options.outWidth.toFloat()
        mPicHeight = options.outHeight.toFloat()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mViewWidth = measuredWidth
        mViewHeight = measuredHeight
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        mViewWidth = measuredWidth
        mViewHeight = measuredHeight
    }

    /**
     * 贝塞尔曲线路径更新事件
     */
    protected class CurveUpdateLister protected constructor(private val mChild: View) :
        AnimatorUpdateListener {
        override fun onAnimationUpdate(animation: ValueAnimator) {
            val value = animation.animatedValue as PointF
            mChild.x = value.x
            mChild.y = value.y
            mChild.alpha = 1 - animation.animatedFraction
        }
    }

    /**
     * 动画结束监听器,用于释放无用的资源
     */
    protected inner class AnimationEndListener protected constructor(
        private val mChild: View,
        private val mParent: ViewGroup,
        private val mAnimatorSet: AnimatorSet
    ) : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            super.onAnimationEnd(animation)
            // 动画结束 移除View
            mParent.removeView(mChild)
            // 从集合中移除
            mAnimatorSets!!.remove(mAnimatorSet)
        }

        init {
            // 缓存
            mAnimatorSets!!.add(mAnimatorSet)
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        // 销毁资源
        destroy()
    }

    /**
     * 销毁资源
     */
    fun destroy() {
        // 取消动画 释放资源
        for (animatorSet in mAnimatorSets!!) {
            // 初始化回调方法
            animatorSet.listeners.clear()
            // 取消动画
            animatorSet.cancel()
        }
        // 释放集合资源
        mAnimatorSets!!.clear()
        mEvaluatorRecord!!.destroy()
    }

    init {
        // 初始化
        init()
    }
}