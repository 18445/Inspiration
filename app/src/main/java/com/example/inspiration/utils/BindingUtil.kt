package com.example.inspiration.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 *
 * @ProjectName:    Inspiration
 * @Package:        com.example.inspiration.utils
 * @ClassName:      BindingUtil
 * @Author:         Yan
 * @CreateDate:     2022年05月01日 01:16:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    BindingUtil
 */

/**
 * Glide 加载loadImage
 */
@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String) {
    val urls = url.split("s")
    Glide.with(view)
        .load(urls[0]+urls[1])
        .circleCrop()
        .into(view)
}

/**
 * 加载完整image
 */
@BindingAdapter("imageTotalUrl")
fun loadTotalImage(view: ImageView, url: String?) {
    if (url != null){
        val urls = url.split("s")
        Glide.with(view)
            .load(urls[0]+urls[1])
            .override(500, 1000)
            .centerCrop()
            .into(view)
    }
}
