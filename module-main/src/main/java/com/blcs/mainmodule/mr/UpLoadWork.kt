package com.blcs.mainmodule.mr

import android.content.Context
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.blcs.comlibs.common.Lg.e

/**
 * @author linweibin
 * @date 2021/9/8
 */
class UpLoadWork(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        //获取传入参数
        val workManager = inputData.getString("workManager")
        e("doWork: $workManager")
        //创建输出结果
        val outputData = Data.Builder().putString("outputData", "sssssssss").build()
        //设置进度
        setProgressAsync(Data.Builder().putInt("progress", 100).build())
        return Result.success(outputData)
    }

    init {
        //设置进度
        setProgressAsync(Data.Builder().putInt("progress", 0).build())
    }
}