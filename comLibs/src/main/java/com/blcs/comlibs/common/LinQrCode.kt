package com.blcs.comlibs.common

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Matrix
import android.widget.ImageView
import com.google.zxing.*
import com.google.zxing.common.BitMatrix
import com.google.zxing.common.HybridBinarizer
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.yzq.zxinglibrary.android.CaptureActivity
import com.yzq.zxinglibrary.bean.ZxingConfig
import com.yzq.zxinglibrary.common.Constant
import com.yzq.zxinglibrary.decode.BitmapLuminanceSource
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * 二维码/条形码工具类
 * 1.生成二维码图片
 * 2.生成带Logo二维码图片
 * 3.生成条形码
 * 4.解析图片中的 二维码 或者 条形码
 * 5.跳转扫描页面
 */
object LinQrCode {
    /**
     * 1.生成二维码图片
     */
    fun createQRCode(content: String?, view: ImageView?) {
        view?.let { createQRCode(content, null, it) }
    }

    /**
     * 2.生成带Logo二维码图片
     */
    fun createQRCode(content: String?, mBitmap: Bitmap?, view: ImageView?) {
        view?.setImageBitmap(createQRCode(content, 500, 1, mBitmap))
    }

    /**
     * 生成带logo的二维码
     * @param url 需要生成二维码的文字、网址等
     * @param size 需要生成二维码的大小（）
     * @param margin 空白边距
     * @param mBitmap logoSize 文件大小
     * @param mBitmap logo文件
     * @return bitmap
     */
    private fun createQRCode(url: String?, size: Int, margin: Int, mBitmap: Bitmap?): Bitmap {
            val IMAGE_HALFWIDTH = size / 10
            val hints = Hashtable<EncodeHintType, Any?>()
            hints[EncodeHintType.CHARACTER_SET] = "utf-8"
            hints[EncodeHintType.ERROR_CORRECTION] = ErrorCorrectionLevel.H
            hints[EncodeHintType.MARGIN] = margin
            val bitMatrix = QRCodeWriter().encode(
                url,
                BarcodeFormat.QR_CODE, size, size, hints
            )
            val width = bitMatrix.width //矩阵高度
            val height = bitMatrix.height //矩阵宽度
            val halfW = width / 2
            val halfH = height / 2
            val mBitmap = mBitmap?.run {
                val m = Matrix()
                val sx = 2.toFloat() * IMAGE_HALFWIDTH / width
                val sy = 2.toFloat() * IMAGE_HALFWIDTH / height
                m.setScale(sx, sy)
                Bitmap.createBitmap(this, 0, 0, width, height, m, false)
            }

            val pixels = IntArray(size * size)
            for (y in 0 until size) {
                for (x in 0 until size) {
                    if (mBitmap != null && x > halfW - IMAGE_HALFWIDTH && x < halfW + IMAGE_HALFWIDTH && y > halfH - IMAGE_HALFWIDTH && y < halfH + IMAGE_HALFWIDTH) {
                        pixels[y * width + x] = mBitmap.getPixel(
                            x - halfW + IMAGE_HALFWIDTH, y - halfH + IMAGE_HALFWIDTH
                        )
                    } else {
                        if (bitMatrix[x, y]) {
                            pixels[y * size + x] = -0x1000000
                        } else {
                            pixels[y * size + x] = -0x1
                        }
                    }
                }
            }
            val bitmap = Bitmap.createBitmap(
                size, size,
                Bitmap.Config.ARGB_8888
            )
            bitmap.setPixels(pixels, 0, size, 0, 0, size, size)
            return bitmap
    }

    /**
     * 3.生成条形码
     */
    fun createBarCode(content: String?, view: ImageView?) {
        view?.setImageBitmap(content?.let { createBarCode(it) })
    }

    fun createBarCode(contents: String): Bitmap? {
        return createBarCode(contents, 1000, 300)
    }

    fun createBarCode(content: String, codeWidth: Int, codeHeight: Int, view: ImageView) {
        view.setImageBitmap(createBarCode(content, codeWidth, codeHeight))
    }

    /**
     * 生成条形码
     * @param content      需要生成的内容
     * @param BAR_WIDTH  生成条形码的宽带
     * @param BAR_HEIGHT 生成条形码的高度
     * @return backgroundColor
     */
    fun createBarCode(content: CharSequence, BAR_WIDTH: Int, BAR_HEIGHT: Int): Bitmap? {
        if (!isNumberOrAlpha(content.toString() + "")!!) {
            return null
        }
        // 条形码的编码类型
        val barcodeFormat: BarcodeFormat = BarcodeFormat.CODE_128
        val backColor = -0x1
        val barCodeColor = -0x1000000
        val writer = MultiFormatWriter()
        var result: BitMatrix? = null
        try {
            result =
                writer.encode(content.toString() + "", barcodeFormat, BAR_WIDTH, BAR_HEIGHT, null)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
        val width: Int? = result?.width
        val height: Int? = result?.height
        val div = height?.let { width?.times(it) }
        val pixels = div?.let { IntArray(it) }
        // All are 0, or black, by default
        for (y in 0 until height!!) {
            val offset = y * width!!
            for (x in 0 until width) {
                pixels?.set(offset + x, if (result?.get(x, y) == true) barCodeColor else backColor)
            }
        }
        val bitmap = width?.let { Bitmap.createBitmap(it, height, Bitmap.Config.ARGB_8888) }
        bitmap?.setPixels(pixels, 0, width, 0, 0, width, height)
        return bitmap
    }

    /**
     * 4.解析图片中的 二维码 或者 条形码
     * @param photo 待解析的图片
     * @return Result 解析结果，解析识别时返回NULL
     */
    fun decodeFromPhoto(photo: Bitmap?): Result? {
        val smallBitmap = photo?.let {
            zoomBitmap(
                it,
                photo.width / 2,
                photo.height / 2
            )
        } // 为防止原始图片过大导致内存溢出，这里先缩小原图显示，然后释放原始Bitmap占用的内存
        //            photo.recycle(); // 释放原始图片占用的内存，防止out of memory异常发生
        val multiFormatReader = MultiFormatReader()

        // 解码的参数
        val hints: Hashtable<DecodeHintType, Any> = Hashtable<DecodeHintType, Any>(2)
        // 可以解析的编码类型
        var decodeFormats: Vector<BarcodeFormat?> = Vector<BarcodeFormat?>()
        if (decodeFormats.isEmpty()) {
            decodeFormats = Vector<BarcodeFormat?>()
            val PRODUCT_FORMATS: Vector<BarcodeFormat> = Vector<BarcodeFormat>(5)
            PRODUCT_FORMATS.add(BarcodeFormat.UPC_A)
            PRODUCT_FORMATS.add(BarcodeFormat.UPC_E)
            PRODUCT_FORMATS.add(BarcodeFormat.EAN_13)
            PRODUCT_FORMATS.add(BarcodeFormat.EAN_8)
            // PRODUCT_FORMATS.add(BarcodeFormat.RSS14);
            val ONE_D_FORMATS: Vector<BarcodeFormat> =
                Vector<BarcodeFormat>(PRODUCT_FORMATS.size + 4)
            ONE_D_FORMATS.addAll(PRODUCT_FORMATS)
            ONE_D_FORMATS.add(BarcodeFormat.CODE_39)
            ONE_D_FORMATS.add(BarcodeFormat.CODE_93)
            ONE_D_FORMATS.add(BarcodeFormat.CODE_128)
            ONE_D_FORMATS.add(BarcodeFormat.ITF)
            val QR_CODE_FORMATS: Vector<BarcodeFormat> = Vector<BarcodeFormat>(1)
            QR_CODE_FORMATS.add(BarcodeFormat.QR_CODE)
            val DATA_MATRIX_FORMATS: Vector<BarcodeFormat> = Vector<BarcodeFormat>(1)
            DATA_MATRIX_FORMATS.add(BarcodeFormat.DATA_MATRIX)

            // 这里设置可扫描的类型，我这里选择了都支持
            decodeFormats.addAll(ONE_D_FORMATS)
            decodeFormats.addAll(QR_CODE_FORMATS)
            decodeFormats.addAll(DATA_MATRIX_FORMATS)
        }
        hints[DecodeHintType.POSSIBLE_FORMATS] = decodeFormats
        // 设置继续的字符编码格式为UTF8
        // hints.put(DecodeHintType.CHARACTER_SET, "UTF8");
        // 设置解析配置参数
        multiFormatReader.setHints(hints)

        // 开始对图像资源解码
        var rawResult = multiFormatReader.decodeWithState(
            BinaryBitmap(
                HybridBinarizer(BitmapLuminanceSource(smallBitmap))
            )
        )
        smallBitmap?.recycle()
        return rawResult
    }

    /**
     * 5.跳转扫描页面
     * @param context
     * @param requestCode 扫描返回码
     */
    fun startScan(context: Activity?, requestCode: Int) {
        val intent = Intent(context, CaptureActivity::class.java)
        /*ZxingConfig是配置类
         *可以设置是否显示底部布局，闪光灯，相册，
         * 是否播放提示音  震动
         * 设置扫描框颜色等
         * 也可以不传这个参数
         * */
        val config = ZxingConfig()
        // config.setPlayBeep(false);//是否播放扫描声音 默认为true
        //  config.setShake(false);//是否震动  默认为true
        // config.setDecodeBarCode(false);//是否扫描条形码 默认为true
//                                config.setReactColor(R.color.colorAccent);//设置扫描框四个角的颜色 默认为白色
//                                config.setFrameLineColor(R.color.colorAccent);//设置扫描框边框颜色 默认无色
//                                config.setScanLineColor(R.color.colorAccent);//设置扫描线的颜色 默认白色
        config.setFullScreenScan(false) //是否全屏扫描  默认为true  设为false则只会在扫描框中扫描
        intent.putExtra(Constant.INTENT_ZXING_CONFIG, config)
        context?.startActivityForResult(intent, requestCode)
    }

    /**
     * 压缩
     * @param bitmap
     * @param width
     * @param height
     * @return
     */
    fun zoomBitmap(bitmap: Bitmap, width: Int, height: Int): Bitmap {
        val w = bitmap.width
        val h = bitmap.height
        val matrix = Matrix()
        val scaleWidth = width.toFloat() / w
        val scaleHeight = height.toFloat() / h
        matrix.postScale(scaleWidth, scaleHeight)
        return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true)
    }

    /**
     * 判断字符类型是否是号码或字母
     */
    fun isNumberOrAlpha(content: String?) :Boolean {
        return content?.let {
            val pNumber = Pattern.compile("[0-9]*")
            var mNumber: Matcher
            val pAlpha = Pattern.compile("[a-zA-Z]")
            var mAlpha: Matcher
            for (i in content.indices) {
                mNumber = pNumber.matcher(content.substring(i, i + 1))
                mAlpha = pAlpha.matcher(content.substring(i, i + 1))
                if (!mNumber.matches() && !mAlpha.matches()) {
                    return false
                }
            }
            return true
        } == false
    }
}