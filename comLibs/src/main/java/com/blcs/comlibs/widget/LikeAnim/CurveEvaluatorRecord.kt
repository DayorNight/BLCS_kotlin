package com.blcs.comlibs.widget.LikeAnim

import android.animation.TypeEvaluator
import android.graphics.PointF
import android.util.SparseArray
import java.util.*

/**
 * @Description: 贝塞尔曲线缓存类
 */
class CurveEvaluatorRecord {
    private val mRandom = Random()
    private var mCurrentPathCounts = 0
    private var mPathArray: SparseArray<TypeEvaluator<PointF>>?

    /**
     * 生成曲线路线
     */
    fun getCurrentPath(pointF1: PointF, pointF2: PointF): TypeEvaluator<PointF> {
        val evaluator: TypeEvaluator<PointF>
        // 记录已生成路径
        ++mCurrentPathCounts
        // 如果已经生成的路径数目超过最大设定，就从路径缓存中随机取一个路径用于绘制，否则新生成一个
        if (mCurrentPathCounts > MAX_PATH_COUNTS) {
            // 读取缓存
            evaluator = mPathArray!![Math.abs(mRandom.nextInt() % MAX_PATH_COUNTS) + 1]
        } else {
            // 创建路径
            evaluator = createPath(pointF1, pointF2)
            // 缓存
            mPathArray!!.put(mCurrentPathCounts, evaluator)
        }
        return evaluator
    }

    /**
     * 生成 贝塞尔路径
     */
    private fun createPath(pointF1: PointF, pointF2: PointF): TypeEvaluator<PointF> {
        return ThreeCurveEvaluator(pointF1, pointF2)
    }

    /**
     * destroy
     */
    fun destroy() {
        if (mPathArray != null) {
            mPathArray!!.clear()
            mPathArray = null
        }
    }

    companion object {
        private const val MAX_PATH_COUNTS = 100
    }

    init {
        mPathArray = SparseArray()
    }
}