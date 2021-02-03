### 应用该库修改
1.bugly   （1）修改 constants.kt   Bugly_AppID 值   （2）替换  build.gradle  中的bugly值

### 库引用
1.卡顿优化： AndroidPerformanceMonitor
https://github.com/markzhai/AndroidPerformanceMonitor/blob/master/README_CN.md
2.内存优化： Leakcanary
https://github.com/square/leakcanary
3.约束布局： constraintlayout
implementation 'androidx.constraintlayout:constraintlayout:2.1.0-alpha1'
4.全局异常捕获
5.代替SP：MMKV
https://github.com/Tencent/MMKV
6.androidP开启反射：FreeReflection
https://github.com/tiann/FreeReflection
7.屏幕适配：AndroidAutoSize
https://github.com/JessYanCoding/AndroidAutoSize
8.动画：lottile
https://github.com/airbnb/lottie-android
9.升级/异常上报：bugly
https://bugly.qq.com/v2/downloads
10.万能适配器：BaseRecyclerViewAdapterHelper
https://github.com/CymChad/BaseRecyclerViewAdapterHelper




### 使用说明
1.bugly 检查app更新 ： MrBugly.checkUpgrade()

distributionUrl=file\:///E:/As/gradle-6.5-bin.zip
