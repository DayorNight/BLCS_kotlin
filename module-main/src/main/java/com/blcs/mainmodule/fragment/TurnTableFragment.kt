package com.blcs.mainmodule.fragment

import android.graphics.Rect
import com.blcs.comlibs.base.BaseFragment
import com.blcs.comlibs.common.*
import com.blcs.mainmodule.R
import com.blcs.mainmodule.databinding.FragmentTurntableBinding


class TurnTableFragment : BaseFragment<FragmentTurntableBinding>(){

    companion object {
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            TurnTableFragment()
        }
    }

    override fun setLayoutId() = R.layout.fragment_turntable

    override fun initUI(binding: FragmentTurntableBinding) {

//        bindView.btnText.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom ->
//                Lg.e("---------left: $left top: $top right: $right bottom: $bottom")
//                Lg.e("=========oldLeft: $oldLeft oldTop: $oldTop oldRight: $oldRight oldBottom: $oldBottom")
//
//            }
//        bindView.btnText.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
//                override fun onViewAttachedToWindow(v: View?) {
//                    Lg.e("===1====")
//                }
//
//                override fun onViewDetachedFromWindow(v: View?) {
//                    Lg.e("===2====")
//                }
//            })
        binding.scroll.viewTreeObserver.addOnGlobalLayoutListener {
                val rect = Rect()
                binding.btnText.getLocalVisibleRect(rect)
                Lg.e("········top: ${rect.top} bottom: ${rect.bottom} left: ${rect.left} right: ${rect.right}")
            }

    }

    override fun initData() {
    }
}