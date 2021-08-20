package com.blcs.mainmodule.bean

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * @author linweibin
 * @date   2021/7/28
 */
class AndroidBodyBean() : MultiItemEntity {
    companion object{
       const val TYPE_SELECT = 1
       const val TYPE_CONTENT = 0
    }
    var title: String? = null
    var problem: String? = null
    var answer: String? = null
    var catalogue: MutableList<String>? = null
    var type: Int = 0
    override val itemType: Int
        get() = type

}