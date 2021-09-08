package com.blcs.mainmodule.fragment

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.TextView
import com.blcs.comlibs.base.BaseFragment
import com.blcs.mainmodule.R
import com.blcs.mainmodule.databinding.FragmentHandlerBinding
import kotlin.concurrent.thread

/**
 * 报错页面
 */
class HandlerFragment : BaseFragment<FragmentHandlerBinding>() {

    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            HandlerFragment()
        }
    }

    override fun setLayoutId() = R.layout.fragment_handler
    override fun initUI(binding: FragmentHandlerBinding) {

    }

    override fun initData() {

    }

    class MyHandler(var tv: TextView,loop: Looper) : Handler(loop) {
        override fun handleMessage(msg: Message) {
            tv.text = msg.obj.toString()
        }

    }
}