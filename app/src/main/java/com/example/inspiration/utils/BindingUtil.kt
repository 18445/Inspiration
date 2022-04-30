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
    Log.d("imageUrl",url)

    Glide.with(view)
        .load(url)
        .into(view)

}
