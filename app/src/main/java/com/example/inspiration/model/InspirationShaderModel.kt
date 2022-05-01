package com.example.inspiration.model

/**
 *
 * @ProjectName:    Inspiration
 * @Package:        com.example.inspiration.model
 * @ClassName:      InspirationShaderModel
 * @Author:         Yan
 * @CreateDate:     2022年05月01日 16:17:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    InspirationShaderModel
 */
data class InspirationShaderModel (
    val colorArray: IntArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as InspirationShaderModel

        if (!colorArray.contentEquals(other.colorArray)) return false

        return true
    }

    override fun hashCode(): Int {
        return colorArray.contentHashCode()
    }
}