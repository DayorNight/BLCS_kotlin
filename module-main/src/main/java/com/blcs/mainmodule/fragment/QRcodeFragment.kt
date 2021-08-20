package com.blcs.mainmodule.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.text.TextUtils
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.blcs.comlibs.base.BaseFragment
import com.blcs.comlibs.common.*
import com.blcs.mainmodule.R
import com.blcs.mainmodule.common.Const
import com.blcs.mainmodule.databinding.FragmentQrCodeBinding
import com.yzq.zxinglibrary.common.Constant

/**
 * 二维码页面
 */
class QRcodeFragment : BaseFragment<FragmentQrCodeBinding>(), View.OnClickListener {

    companion object {
        const val REQUEST_CODE = 200
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            QRcodeFragment()
        }
    }

    override fun setLayoutId() = R.layout.fragment_qr_code

    var bindView: FragmentQrCodeBinding? = null
    override fun initUI(bindView: FragmentQrCodeBinding) {
        this.bindView = bindView
        bindView.btnCopy = View.GONE
        //识别二维码/条形码
        bindView.ivBarQr.setOnLongClickListener(View.OnLongClickListener {
            val bitmap = (bindView.ivBarQr.drawable as BitmapDrawable).bitmap
            val result = LinQrCode.decodeFromPhoto(bitmap)
            bindView.scanContent = result?.text
            false
        })

        bindView.tvCode.addTextChangedListener { it ->
            it?.apply {
                bindView.btnCopy =if (length>0) View.VISIBLE else View.GONE
            }
        }

        bindView.btnQrCode.setOnClickListener(this)
        bindView.btnBarCode.setOnClickListener(this)
        bindView.btnCustomQrCode.setOnClickListener(this)
        bindView.btnScanCode.setOnClickListener(this)
        bindView.tvCopy.setOnClickListener(this)
        bindView.tvCode.setOnClickListener(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val content = data?.getStringExtra(Constant.CODED_CONTENT)
            bindView?.scanContent = content;
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        //权限申请成功
        if (requestCode == REQUEST_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            LinQrCode.startScan(activity, REQUEST_CODE)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_code -> toFragment(Const.Fragment_WEB)
            R.id.tv_copy -> bindView?.scanContent?.let { LinCommon.copyText(activity, it) }
            R.id.btn_QrCode -> bindView?.apply {
                if (TextUtils.isEmpty(inputContent)){
                    getString(R.string.str_qr_input).toast(activity)
                    return
                }
                LinQrCode.createQRCode(inputContent, ivBarQr)
            }
            R.id.btn_barCode ->  bindView?.apply {
                if (TextUtils.isEmpty(inputContent)) {
                    getString(R.string.str_qr_input).toast(activity)
                    return
                }
                if (LinQrCode.isNumberOrAlpha(inputContent)) {
                    LinQrCode.createBarCode(inputContent,ivBarQr)
                    return
                }

                "只限数字与字母".toast(activity)
            }
            R.id.btn_custom_qrCode ->  bindView?.apply {
                    if (TextUtils.isEmpty(inputContent)) {
                        getString(R.string.str_qr_input).toast(activity)
                        return
                    }
                    val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_app512)
                    LinQrCode.createQRCode(inputContent, bitmap,ivBarQr)
                }

            R.id.btn_scan_code -> {
                val hasPermiss = context?.let {
                    ContextCompat.checkSelfPermission(
                        it,
                        Manifest.permission.CAMERA
                    )
                } !== PackageManager.PERMISSION_GRANTED

                if (hasPermiss) { //申请权限
                    requestPermissions(arrayOf(Manifest.permission.CAMERA),
                        QRcodeFragment.REQUEST_CODE
                    )
                } else { //已申请
                    LinQrCode.startScan(activity, QRcodeFragment.REQUEST_CODE)
                }
            }
        }
    }

    override fun initData() {
    }

}