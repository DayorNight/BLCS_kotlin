package com.blcs.mainmodule.adapter

import android.view.Gravity
import android.widget.TextView
import com.blcs.comlibs.common.Lg
import com.blcs.mainmodule.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * text adapter
 */
class SimpleListAdapter(datas: MutableList<Any>,var gravity: Int = Gravity.CENTER) : BaseQuickAdapter<Any, BaseViewHolder>(R.layout.adapter_list,datas) {


    override fun convert(holder: BaseViewHolder, item: Any) {
        Lg.e("========"+item.toString())
        holder.setText(R.id.tv_adapter,item.toString())
        holder.getView<TextView>(R.id.tv_adapter).gravity = gravity
    }

}