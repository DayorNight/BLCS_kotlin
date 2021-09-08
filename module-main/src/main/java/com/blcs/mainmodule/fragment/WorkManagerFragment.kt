package com.blcs.mainmodule.fragment

import android.net.Uri
import android.text.method.ScrollingMovementMethod
import androidx.work.*
import com.blcs.comlibs.base.BaseFragment
import com.blcs.comlibs.common.Lg
import com.blcs.mainmodule.R
import com.blcs.mainmodule.databinding.FragmentWorkmanagerBinding
import com.blcs.mainmodule.mr.UpLoadWork
import java.util.concurrent.TimeUnit

/**
 * WorkManager页面
 */
class WorkManagerFragment : BaseFragment<FragmentWorkmanagerBinding>() {

    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            WorkManagerFragment()
        }
    }

    override fun setLayoutId() = R.layout.fragment_workmanager
    private var workManager: WorkManager? = null
    var request: OneTimeWorkRequest? = null
    override fun initUI(binding: FragmentWorkmanagerBinding) {
        Lg.e("=----")
        binding.tvContent.movementMethod = ScrollingMovementMethod.getInstance()
        binding.tvContent.text = "override fun initWorkManager() {\n" +
                "    //构建约束\n" +
                "    Constraints.Builder().apply {\n" +
                "        //NetworkType.NOT_REQUIRED 不需要网络  默认：\n" +
                "        //NetworkType.CONNECTED    需要网络\n" +
                "        //NetworkType.UNMETERED    不计费的网络比如WIFI下执行\n" +
                "        //NetworkType.NOT_ROAMING  非漫游网络状态\n" +
                "        //NetworkType.METERED      计费网络比如3G，4G下执行\n" +
                "        setRequiredNetworkType(NetworkType.CONNECTED)\n" +
                "        //指定电量在可接受范围内运行\n" +
                "        setRequiresBatteryNotLow(true)\n" +
                "        //指定在存储量在可接受范围内运行\n" +
                "        setRequiresStorageNotLow(true)\n" +
                "        //当设备处于充电状态时运行\n" +
                "        setRequiresCharging(true)\n" +
                "        //当设备处于空闲状态时运行\n" +
                "        setRequiresDeviceIdle(true)\n" +
                "        //当Uri发生变化的时候运行\n" +
                "        val uri = Uri.parse(\"xxxxxxx\")\n" +
                "        addContentUriTrigger(uri, true)\n" +
                "        //设置内容变化到任务被布置间的最大延迟(毫秒).\n" +
                "        setTriggerContentMaxDelay(1, TimeUnit.MILLISECONDS)\n" +
                "    }\n" +
                "    val data = Data.Builder().putString(\"workManager\", \"开始执行\").build()\n" +
                "    //创建请求\n" +
                "    val request = OneTimeWorkRequest.Builder(UpLoadWork::class.java)\n" +
                "            //延迟初始化\n" +
                "            .setInitialDelay(1, TimeUnit.MILLISECONDS)\n" +
                "            //重试策略\n" +
                "            .setBackoffCriteria(\n" +
                "                BackoffPolicy.LINEAR,\n" +
                "                OneTimeWorkRequest.MIN_BACKOFF_MILLIS,\n" +
                "                TimeUnit.MILLISECONDS\n" +
                "            )\n" +
                "            //携带参数\n" +
                "            .setInputData(data)\n" +
                "            .addTag(\"tag\")\n" +
                "            .build()\n" +
                "    //执行请求\n" +
                "    workManager = activity?.let { WorkManager.getInstance(it) }\n" +
                "    workManager?.enqueue(request)\n" +
                "    workManager?.getWorkInfoByIdLiveData(request.id)?.observe(this, { workInfo ->\n" +
                "        val value = workInfo.progress.getInt(\"progress\", 0)\n" +
                "        Lg.e(\"progress: \" + value)\n" +
                "        Lg.e(\"initUI: \" + workInfo.state)\n" +
                "        Lg.e(\"initUI: \" + workInfo.outputData.getString(\"outputData\"))\n" +
                "\n" +
                "    })\n" +
                "}\n" +
                "\n" +
                "\n" +
                "class UpLoadWork(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {\n" +
                "    override fun doWork(): Result {\n" +
                "        //获取传入参数\n" +
                "        val workManager = inputData.getString(\"workManager\")\n" +
                "        e(\"doWork: $workManager\")\n" +
                "        //创建输出结果\n" +
                "        val outputData = Data.Builder().putString(\"outputData\", \"sssssssss\").build()\n" +
                "        //设置进度\n" +
                "        setProgressAsync(Data.Builder().putInt(\"progress\", 100).build())\n" +
                "        return Result.success(outputData)\n" +
                "    }\n" +
                "\n" +
                "    init {\n" +
                "        //设置进度\n" +
                "        setProgressAsync(Data.Builder().putInt(\"progress\", 0).build())\n" +
                "    }\n" +
                "}"
        //构建约束
        Constraints.Builder().apply {
            //NetworkType.NOT_REQUIRED 不需要网络  默认：
            //NetworkType.CONNECTED    需要网络
            //NetworkType.UNMETERED    不计费的网络比如WIFI下执行
            //NetworkType.NOT_ROAMING  非漫游网络状态
            //NetworkType.METERED      计费网络比如3G，4G下执行
            setRequiredNetworkType(NetworkType.CONNECTED)
            //指定电量在可接受范围内运行
            setRequiresBatteryNotLow(true)
            //指定在存储量在可接受范围内运行
            setRequiresStorageNotLow(true)
            //当设备处于充电状态时运行
            setRequiresCharging(true)
            //当设备处于空闲状态时运行
            setRequiresDeviceIdle(true)
            //当Uri发生变化的时候运行
            val uri = Uri.parse("xxxxxxx")
            addContentUriTrigger(uri, true)
            //设置内容变化到任务被布置间的最大延迟(毫秒).
            setTriggerContentMaxDelay(1, TimeUnit.MILLISECONDS)
        }
        val data = Data.Builder().putString("workManager", "开始执行").build()
        //创建请求
        val request = OneTimeWorkRequest.Builder(UpLoadWork::class.java)
                //延迟初始化
                .setInitialDelay(1, TimeUnit.MILLISECONDS)
                //重试策略
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    OneTimeWorkRequest.MIN_BACKOFF_MILLIS,
                    TimeUnit.MILLISECONDS
                )
                //携带参数
                .setInputData(data)
                .addTag("tag")
                .build()
        //执行请求
        workManager = activity?.let { WorkManager.getInstance(it) }
        workManager?.enqueue(request)
        workManager?.getWorkInfoByIdLiveData(request.id)?.observe(this, { workInfo ->
            val value = workInfo.progress.getInt("progress", 0)
            Lg.e("progress: " + value)
            Lg.e("initUI: " + workInfo.state)
            Lg.e("initUI: " + workInfo.outputData.getString("outputData"))

        })
    }

    fun stopWork() {
        //需求任务
        request?.id?.let { workManager?.cancelWorkById(it) }
    }

    override fun initData() {

    }

}