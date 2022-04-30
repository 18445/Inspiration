package com.example.inspiration.ui.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.inspiration.R
import com.example.inspiration.base.BaseActivity
import com.example.inspiration.databinding.ActivityMainBinding


class MainActivity : BaseActivity() {


    private lateinit var activityMainBinding: ActivityMainBinding

    override fun initData() {

    }

    override fun initView() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setNavigation()
    }

    /**
     * 设置底部导航绑定操作
     */
    private fun setNavigation(){
        activityMainBinding.bottomNavigationView.also {
            val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
            NavigationUI.setupWithNavController(it,navController)
        }
    }


}