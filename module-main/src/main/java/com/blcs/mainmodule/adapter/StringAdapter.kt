package com.blcs.mainmodule.adapter

import com.blcs.mainmodule.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * @author linweibin
 * @date   2021/8/20
 */
class StringAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.include_string) {

    override fun convert(holder: BaseViewHolder, item: String) {
        holder.setText(R.id.tv_content,item)
    }
}