package com.blcs.comlibs.common

import android.os.Parcelable
import com.tencent.mmkv.MMKV



/**
 * 存本地数据
 */
object LinMMKV {
    const val KEY_GUIDE = "引导页"//第一次安装开启
    /**
     * 存放数据
     */
    fun put(key: String?, any: Any?) =
        MMKV.defaultMMKV().run {
            when (any) {
                is Boolean -> this?.encode(key, any)
                is String -> this?.encode(key, any)
                is Int -> this?.encode(key, any)
                is Long -> this?.encode(key, any)
                is Float -> this?.encode(key, any)
                is Double -> this?.encode(key, any)
                is ByteArray -> this?.encode(key, any)
                is Parcelable -> this?.encode(key, any)
                is Set<*> -> this?.encode(key, any as Set<String>)
                else -> false
            }
        }

    /**
     * 获取数据
     */
    fun <T> get(key: String?, defaultValue: T?) =
        MMKV.defaultMMKV().run {
            when (defaultValue) {
                is Boolean -> this?.decodeBool(key,defaultValue)
                is String -> this?.decodeString(key,defaultValue)
                is Int -> this?.decodeInt(key,defaultValue)
                is Double -> this?.decodeDouble(key,defaultValue)
                is Float -> this?.decodeFloat(key,defaultValue)
                is Long -> this?.decodeLong(key,defaultValue)
                is ByteArray -> this?.decodeBytes(key,defaultValue)
                is Set<*> -> this?.decodeStringSet(key,defaultValue as Set<String>)
                else -> ""
            }
        }

    /**
     * 获取 Parcelable 数据
     */
    fun <T : Parcelable?> get(key: String?, tClass: Class<T>?) =
        MMKV.defaultMMKV()?.decodeParcelable(key,tClass)
}