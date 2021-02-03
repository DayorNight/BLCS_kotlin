package com.blcs.comlibs.widget.LikeAnim

import android.animation.TypeEvaluator
import android.graphics.PointF

/**
 * @Description: 三阶贝赛尔曲线
 */
class ThreeCurveEvaluator(private val mControlP1: PointF, private val mControlP2: PointF) :
    TypeEvaluator<PointF> {
    override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
        val pointCur = PointF()
        val leftTime = 1.0f - fraction
        // 三阶贝赛尔曲线
        pointCur.x = Math.pow(leftTime.toDouble(), 3.0).toFloat() * startValue.x + 3 * Math.pow(
            leftTime.toDouble(),
            2.0
        ).toFloat() * fraction * mControlP1.x + 3 * leftTime * Math.pow(fraction.toDouble(), 2.0)
            .toFloat() * mControlP2.x + Math.pow(fraction.toDouble(), 3.0).toFloat() * endValue.x
        pointCur.y = Math.pow(leftTime.toDouble(), 3.0).toFloat() * startValue.y + 3 * Math.pow(
            leftTime.toDouble(),
            2.0
        )
            .toFloat() * fraction * mControlP1.y + 3 * leftTime * fraction * fraction * mControlP2.y + Math.pow(
            fraction.toDouble(),
            3.0
        ).toFloat() * endValue.y
        return pointCur
    }
}