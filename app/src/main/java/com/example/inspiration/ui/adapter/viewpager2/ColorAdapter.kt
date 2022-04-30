package com.example.inspiration.ui.adapter.viewpager2

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 *
 * @ProjectName:    Inspiration
 * @Package:        com.example.inspiration.ui.adapter.viewpager2
 * @ClassName:      ColorAdapter
 * @Author:         Yan
 * @CreateDate:     2022年04月30日 16:41:00
 * @UpdateRemark:   更新说明：
 * @Version:        1.0
 * @Description:    色谱的vp2适配器
 */
class ColorAdapter(
    frgManager: FragmentManager,
    fragments: MutableList<Fragment>,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(frgManager, lifecycle) {

    private val fragmentList: MutableList<Fragment> = fragments

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}