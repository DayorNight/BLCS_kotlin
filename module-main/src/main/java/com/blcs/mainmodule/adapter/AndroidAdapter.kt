package com.blcs.mainmodule.adapter

import android.graphics.Color
import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.fastjson.JSON
import com.blcs.comlibs.common.LinRecycler
import com.blcs.comlibs.common.getJson
import com.blcs.comlibs.widget.dialogFragment.LinCustomDialogFragment
import com.blcs.mainmodule.R
import com.blcs.mainmodule.bean.AndroidBean
import com.blcs.mainmodule.bean.AndroidBodyBean
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * text adapter
 */
class AndroidAdapter(private val parentFragmentManager: FragmentManager) :
    BaseMultiItemQuickAdapter<AndroidBodyBean, BaseViewHolder>(){
    init {
        addItemType(AndroidBodyBean.TYPE_CONTENT, R.layout.adapter_android)
        addItemType(AndroidBodyBean.TYPE_SELECT, R.layout.tool_recyclerview)
    }

    var list = mutableListOf("设计模式", "JAVA", "Android", "其他问题", "HR问题")
    var javas = mutableListOf("java基础1", "java基础2", "java基础3", "java集合", "java网络", "java进程")
    var androids = mutableListOf("Android进阶1", "Android进阶2", "Android进阶3", "Android进阶4")
    var androidOthers = mutableListOf("Android其他1", "Android其他2", "Android其他3", "Android其他4")
    var catalogues = mutableListOf(javas, androids, androidOthers)
    var jsons = mutableListOf("设计模式", "java基础1", "java基础2", "java基础3", "java集合", "java网络", "java进程",
        "Android进阶1", "Android进阶2", "Android进阶3", "Android进阶4")
    var changeStyle: Boolean = false
    override fun convert(holder: BaseViewHolder, item: AndroidBodyBean) {
        val adapterPosition = holder.adapterPosition
        when (item.itemType) {
            AndroidBodyBean.TYPE_SELECT -> {
                val mRv = holder.getView<RecyclerView>(R.id.tool_recyclerView)
                val mAdapter = StringAdapter()
                LinRecycler.init(context, LinearLayoutManager.VERTICAL, mAdapter, mRv, false)
                mAdapter.data = item.catalogue!!
                mAdapter.notifyDataSetChanged()
                mAdapter.setOnItemClickListener { adapter, _, position ->
                    val item = adapter.data[position].toString()
                    if (!TextUtils.isEmpty(item) && jsons.contains(item)) {
                        getJsonData(item)
                        recyclerView.scrollToPosition(adapterPosition)
                    } else {
                        checkArticle(adapterPosition, position)
                    }
                }
            }
            AndroidBodyBean.TYPE_CONTENT -> {
                holder.setText(R.id.tv_title, item.title)
                val plus = holder.adapterPosition + 1
                holder.setText(R.id.tv_content, plus.toString().plus("、") + item.problem)

                var resolever = holder.getView<TextView>(R.id.tv_resolver)
                val clickNext = holder.getView<TextView>(R.id.tv_next)
                val clickPre = holder.getView<TextView>(R.id.tv_pre)
                var resolever2 = holder.getView<TextView>(R.id.tv_resolve2)
                val clickNext2 = holder.getView<TextView>(R.id.tv_next2)
                val clickPre2 = holder.getView<TextView>(R.id.tv_pre2)
                resolever.setOnClickListener { toastResolever(item)}
                resolever2.setOnClickListener { toastResolever(item)}
                clickNext.setOnClickListener { nextPage(adapterPosition) }
                clickNext2.setOnClickListener { nextPage(adapterPosition) }
                clickPre.setOnClickListener { prePage(adapterPosition) }
                clickPre2.setOnClickListener { prePage(adapterPosition) }
                holder.getView<CardView>(R.id.cv_content).setOnLongClickListener {
                    LinCustomDialogFragment.init().setTitle("修改样式")
                        .setContentColor(Color.BLUE)
                        .setContent("是否修改样式")
                        .setType(LinCustomDialogFragment.TYPE_SURE)
                        .setOnClickListener {
                            changeStyle = !changeStyle
                            notifyDataSetChanged()
                        }
                        .show(parentFragmentManager)
                    return@setOnLongClickListener false
                }

                holder.setGone(R.id.ll_style2, !changeStyle)
                holder.setGone(R.id.fl_style, changeStyle)
                holder.setGone(R.id.tv_page, changeStyle)
                if (changeStyle) {
                    holder.setText(R.id.tv_page2, "${adapterPosition.plus(1)}/${data.size}")
                    clickNext2.visibility = if (adapterPosition == data.size - 1) View.GONE else View.VISIBLE
                    clickPre2.visibility = if (adapterPosition == 0) View.GONE else View.VISIBLE
                } else {
                    holder.setText(R.id.tv_page, "${adapterPosition.plus(1)}/${data.size}")
                    clickNext.visibility =
                        if (adapterPosition == data.size - 1) View.GONE else View.VISIBLE
                    clickPre.visibility = if (adapterPosition == 0) View.GONE else View.VISIBLE
                }

            }
        }
    }

    private fun prePage(adapterPosition: Int) {
        recyclerView.smoothScrollToPosition(adapterPosition - 1)
    }

    private fun nextPage(adapterPosition: Int) {
        recyclerView.smoothScrollToPosition(adapterPosition + 1)
    }

    fun checkArticle(curPos: Int, pos: Int) {
        var datas = mutableListOf<AndroidBodyBean>()
        val androidBodyBean = AndroidBodyBean()
        androidBodyBean.type = 1
        if (pos == -1) {//第一层目录
            androidBodyBean.catalogue = list
            datas?.add(androidBodyBean)
        } else if (pos in 1..3) {//第二层目录
            androidBodyBean.catalogue = catalogues[pos - 1]
            datas?.add(androidBodyBean)
        }
        data = datas
        notifyDataSetChanged()
        recyclerView.scrollToPosition(curPos + 1)
    }

    fun getJsonData(json: String) {
        val jsonFile = json.plus(".json")
        context.apply {
            val androidBean = JSON.parseObject(jsonFile.getJson(this), AndroidBean::class.java)
            val jsons = androidBean.body?.toMutableList()
            data = jsons?.toMutableList()!!
            notifyDataSetChanged()
        }
    }

    fun toastResolever(item: AndroidBodyBean) {
        LinCustomDialogFragment.init().setTitle("参考提示")
            .setContentColor(Color.BLUE)
            .setContent(item.answer)
            .setType(LinCustomDialogFragment.TYPE_SURE)
            .setOnClickListener {  }
            .show(parentFragmentManager)
    }


}