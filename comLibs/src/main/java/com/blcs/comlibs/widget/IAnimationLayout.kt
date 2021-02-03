package com.blcs.comlibs.widget

/**
 * @ClassName: IAnimationLayout
 * @Author: KaiSenGao
 * @CreateDate: 2020/12/24 10:57
 * @Description:
 */
interface IAnimationLayout {
    /**
     * 添加 资源文件
     *
     * @param resId resId
     */
    fun addLikeImage(resId: Int)

    /**
     * 添加 资源文件组
     *
     * @param resIds resIds
     */
    fun addLikeImages(vararg resIds: Int?)

    /**
     * 添加 资源文件列表
     *
     * @param resIds resIds
     */
    fun addLikeImages(resIds: List<Int?>?)

    /**
     * 添加 发送
     */
    fun addFavor()
}