package com.example.inspiration.utils

import android.graphics.Canvas
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.allViews
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.inspiration.R
import com.example.inspiration.ui.adapter.core.ListAdapter
import kotlin.math.abs


/**
 *
 * @ProjectName:    Inspiration
 * @Package:        com.example.inspiration.utils
 * @ClassName:      StarCardItemTouchHelper
 * @Author:         Yan
 * @CreateDate:     2022年05月02日 15:48:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    StarCardItemTouchHelper
 */
class StarCardItemTouchHelper (val listAdapter : ListAdapter): ItemTouchHelper.Callback() {
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {

        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN //允许上下的拖动
        val swipeFlags = ItemTouchHelper.LEFT //只允许从右向左侧滑
        return makeMovementFlags(dragFlags, swipeFlags)

    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        //onItemMove是接口方法
        listAdapter.move(viewHolder.absoluteAdapterPosition,target.absoluteAdapterPosition);
        return true;
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        listAdapter.removeAt(viewHolder.absoluteAdapterPosition
        )
    }

    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    //限制ImageView长度所能增加的最大值
    private val ICON_MAX_SIZE = 50.0f

    //ImageView的初始长宽
    private val fixedWidth = 150f


    //重置改变，防止由于复用而导致的显示问题
    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)

        viewHolder.itemView.scrollX = 0
        viewHolder.itemView.findViewById<TextView>(R.id.tv_collect_delete).text = "左滑删除"

        val img = viewHolder.itemView.findViewById<ImageView>(R.id.iv_collect_delete)


        val params = img.layoutParams as FrameLayout.LayoutParams

        params.width = 150
        params.height = 150

        img.layoutParams = params
        img.visibility = View.INVISIBLE
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        //仅对侧滑状态下的效果做出改变
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            //如果dX小于等于删除方块的宽度，那么我们把该方块滑出来

            Log.d("ACTION_STATE_SWIPE",getSlideLimitation(viewHolder).toString())

            if (abs(dX) <= getSlideLimitation(viewHolder)) {
                viewHolder.itemView.scrollTo((-dX).toInt(), 0)
                viewHolder.itemView.findViewById<FrameLayout>(R.id.fl_collect).scrollTo(dX.toInt(),0)
            }
            else if (abs(dX) <= recyclerView.width / 2) {
                val tv = viewHolder.itemView.findViewById<TextView>(R.id.tv_collect_delete)
                val img = viewHolder.itemView.findViewById<ImageView>(R.id.iv_collect_delete)

                val distance = (recyclerView.width / 2 - getSlideLimitation(viewHolder)).toDouble()
                val factor = ICON_MAX_SIZE / distance
                var diff = (abs(dX) - getSlideLimitation(viewHolder)) * factor
                if (diff >= ICON_MAX_SIZE) diff = ICON_MAX_SIZE.toDouble()

                tv.text = "" //把文字去掉
                img.visibility = View.VISIBLE //显示眼睛

                val params = img.layoutParams as FrameLayout.LayoutParams
                params.width = ((fixedWidth + diff).toInt())
                params.height = ((fixedWidth + diff).toInt())
                img.layoutParams = params
            }
        } else {
            //拖拽状态下不做改变，需要调用父类的方法
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        }
    }

    /**
     * 获取删除方块的宽度
     */
    private fun getSlideLimitation(viewHolder: RecyclerView.ViewHolder): Int {

        return viewHolder.itemView.findViewById<FrameLayout>(R.id.fl_collect).layoutParams.width / 2
    }
}