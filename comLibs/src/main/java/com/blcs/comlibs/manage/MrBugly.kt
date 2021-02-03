package com.blcs.comlibs.manage

import android.content.Context
import android.os.Environment
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import com.blcs.comlibs.common.Lg
import com.tencent.bugly.beta.Beta
import com.tencent.bugly.beta.UpgradeInfo
import com.tencent.bugly.beta.ui.UILifecycleListener

/**
 * Bugly 升级工具
 */
object MrBugly {

    /**
     * 自动初始化开关
     * @param true 启动自动初始化升级模块; false不会自动初始化;
     */
    fun initAutoUpgrade(context: Context,boolean: Boolean){
        Beta.init(context, boolean)
    }

    /**
     * 自动检查更新开关
     */
    fun autoCheckUpgrade(boolean: Boolean){
        Beta.autoCheckUpgrade = boolean
    }
    /**
     * 检测更新
     * @Des 手动调用
     */
    fun checkUpgrade(){
        Beta.checkUpgrade()
    }

    /**
     * 升级检查周期设置
     * @Des 设置升级检查周期为60s(默认检查周期为0s)，60s内SDK不重复向后台请求策略);
     */
    fun upgradeCheckPeriod(time: Long){
        Beta.upgradeCheckPeriod = time
    }

    /**
     * 延迟初始化
     * @Des 设置启动延时为1s（默认延时3s），APP启动1s后初始化SDK，避免影响APP启动速度;
     */
    fun initDelay(time: Long){
        Beta.initDelay = time
    }

    /**
     * 设置通知栏大图标
     */
    fun setLargeIconId(@IdRes srcId: Int){
        Beta.largeIconId = srcId
    }

    /**
     * 设置状态栏小图标
     */
    fun setSmallIconId(@IdRes srcId: Int){
        Beta.smallIconId = srcId
    }

    /**
     * 设置更新弹窗默认展示的banner
     * @Des defaultBannerId为项目中的图片资源Id; 当后台配置的banner拉取失败时显示此banner，默认不设置则展示“loading...“;
     */
    fun setDefaultBannerId(@IdRes srcId: Int){
        Beta.defaultBannerId = srcId
    }

    /**
     * 设置sd卡的Download为更新资源存储目录
     * @Des 后续更新资源会保存在此目录，需要在manifest中添加WRITE_EXTERNAL_STORAGE权限;
     */
    fun setStorageDir(){
        Beta.storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
    }

    /**
     * 设置开启显示打断策略
     * @Des 设置点击过确认的弹窗在App下次启动自动检查更新时会再次显示。
     */
    fun setStrategy(){
        Beta.showInterruptedStrategy = true
    }

    /**
     * 添加可显示弹窗的Activity
     * @Des 只允许在MainActivity上显示更新弹窗，其他activity上不显示弹窗; 如果不设置默认所有activity都可以显示弹窗。
     */
    fun setShowUpgradeActs(){
//        Beta.canShowUpgradeActs.add(MainActivity.class);
    }

    /**
     * 设置自定义升级对话框UI布局
     * upgrade_dialog为项目的布局资源。 注意：因为要保持接口统一，需要用户在指定控件按照以下方式设置tag，
     * 否则会影响您的正常使用： - 特性图片：beta_upgrade_banner，如：android:tag="beta_upgrade_banner"
     * 标题：beta_title，如：android:tag="beta_title"
     * 升级信息：beta_upgrade_info 如： android:tag="beta_upgrade_info"
     * 更新属性：beta_upgrade_feature 如： android:tag="beta_upgrade_feature"
     * 取消按钮：beta_cancel_button 如：android:tag="beta_cancel_button"
     * 确定按钮：beta_confirm_button 如：android:tag="beta_confirm_button"
     */
    fun setUpgradeDialogUi(@LayoutRes layoutRes: Int){
        Beta.upgradeDialogLayoutId = layoutRes
    }

    /**
     * 设置自定义tip弹窗UI布局
     * 注意：因为要保持接口统一，需要用户在指定控件按照以下方式设置tag，否则会影响您的正常使用：
     * 标题：beta_title，如：android:tag="beta_title"
     * 提示信息：beta_tip_message 如： android:tag="beta_tip_message"
     * 取消按钮：beta_cancel_button 如：android:tag="beta_cancel_button"
     * 确定按钮：beta_confirm_button 如：android:tag="beta_confirm_button"
     */
    fun setTipsDialogLayoutId(@LayoutRes layoutRes: Int){
        Beta.tipsDialogLayoutId = layoutRes
    }

    /**
     * 设置升级对话框生命周期回调接口
     */
    fun upGradeDialogLifecycleListener(){
        Beta.upgradeDialogLifecycleListener = object : UILifecycleListener<UpgradeInfo?> {
            override fun onCreate(context: Context?, view: View?, upgradeInfo: UpgradeInfo?) {
                Lg.d("onCreate")
            }

            override fun onStart(context: Context?, view: View?, upgradeInfo: UpgradeInfo?) {
                Lg.d("onStart")
            }

            override fun onResume(context: Context?, view: View, upgradeInfo: UpgradeInfo?) {
//                Lg.d("onResume")
//                // 注：可通过这个回调方式获取布局的控件，如果设置了id，可通过findViewById方式获取，如果设置了tag，可以通过findViewWithTag，具体参考下面例子:
//
//                // 通过id方式获取控件，并更改imageview图片
//                val imageView: ImageView = view.findViewById(R.id.icon) as ImageView
//                imageView.setImageResource(R.mipmap.ic_launcher)
//
//                // 通过tag方式获取控件，并更改布局内容
//                view.findViewWithTag("textview").text = "my custom text"
//
//                // 更多的操作：比如设置控件的点击事件
//                imageView.setOnClickListener(object : View.OnClickListener {
//                    override fun onClick(v: View?) {
//                        val intent = Intent(
//                            getApplicationContext(),
//                            OtherActivity::class.java
//                        )
//                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//                        startActivity(intent)
//                    }
//                })
            }

            override fun onPause(context: Context?, view: View?, upgradeInfo: UpgradeInfo?) {
                Lg.d("onPause")
            }

            override fun onStop(context: Context?, view: View?, upgradeInfo: UpgradeInfo?) {
                Lg.d("onStop")
            }

            override fun onDestroy(context: Context?, view: View?, upgradeInfo: UpgradeInfo?) {
                Lg.d("onDestory")
            }
        }
    }

    /**
     * 设置是否显示消息通知
     * @Des 如果你不想在通知栏显示下载进度，你可以将这个接口设置为false，默认值为true。
     */
    fun setShowNotification(boolean: Boolean){
        Beta.enableNotification = boolean
    }

    /**
     * 设置Wifi下自动下载
     */
    fun autoDownloadOnWifi(boolean: Boolean){
        Beta.autoDownloadOnWifi  = boolean
    }
    /**
     * 设置是否显示弹窗中的apk信息
     */
    fun canShowApkInfo(boolean: Boolean){
        Beta.canShowApkInfo   = boolean
    }
    /**
     * 关闭热更新能力
     */
    fun enableHotfix(boolean: Boolean){
        Beta.enableHotfix    = boolean
    }
}