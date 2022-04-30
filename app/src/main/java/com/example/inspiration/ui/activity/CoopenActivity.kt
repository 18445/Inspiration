package com.example.inspiration.ui.activity



import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.inspiration.R
import com.example.inspiration.base.BaseActivity
import com.example.inspiration.constant.Constant
import com.example.inspiration.databinding.ActivityCoopenBinding
import com.example.inspiration.ui.viewModel.CoopenViewModel
import com.example.inspiration.utils.onSafeClick
import com.example.inspiration.utils.toast
import com.google.android.material.textfield.TextInputLayout
import com.tencent.mmkv.MMKV
import kotlin.math.max


class CoopenActivity : BaseActivity() {


    private var mArcHeaderHeight = 0

    //上方和下方两个不同的alpha通道
    private val mBtnAlphaTop : MutableLiveData<Float> = MutableLiveData()
    private val mBtnAlphaBottom : MutableLiveData<Float> = MutableLiveData()

    private lateinit var mCoopenViewModel: CoopenViewModel
    private lateinit var coopenBinding: ActivityCoopenBinding

    //1/2 1/4 屏幕的大小
    private val mHalfScreenHeight by lazy {
        mScreenHeight / 2
    }
    private val mQuarterScreenHeight by lazy {
        mScreenHeight / 4
    }

    override fun initData() {
        mBtnAlphaBottom.value = 1f
        mBtnAlphaTop.value = 0f
        mCoopenViewModel = ViewModelProvider(this).get(CoopenViewModel::class.java)
    }

    override fun initView() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        coopenBinding  = DataBindingUtil.setContentView(this, R.layout.activity_coopen)

        coopenBinding.also {
            it.bottomText = "Register now"
            it.topText = "Login in"
        }


        coopenBinding.logo.also {
            it.fillLogoTextArray("Welcome To Inspiration")
            it.startAnim()
        }

        coopenBinding.ahvTop.also { arcHeaderView ->

            //获得高度
            arcHeaderView.post {
                mArcHeaderHeight = if(mArcHeaderHeight == 0) arcHeaderView.measuredHeight else mArcHeaderHeight
                    Log.d("getHeight",mArcHeaderHeight.toString()+"  "+arcHeaderView.measuredHeight.toString())
            }

            //回调
            arcHeaderView.arcHeightChange {

//                  上方ArcView收缩状态 -> 拉伸状态
                    if(mArcHeaderHeight != 0){
                        mBtnAlphaBottom.value = foldAlpha(it)
                    }

//                  下方ArcView拉伸状态 -> 收缩状态
                    mBtnAlphaTop.value = unFoldAlpha(it)

            }
        }

        //底部Button
        coopenBinding.mbtnLoginBottom.also {
            mBtnAlphaBottom.observe(this){ mAlpha ->
                coopenBinding.btnAlphaBottom = mAlpha
                it.isClickable = mAlpha > 0
//                coopenBinding.tiedLoginPhone.isEnabled = mAlpha > 0
            }

            it.animate().duration = 1250
            it.animate().interpolator = AccelerateDecelerateInterpolator()
            it.animate().translationY(-450f).startDelay = 500
            it.animate().start()

            it.onSafeClick({
//                val loginPhone = mCoopenViewModel.getUserLoginPhone() ?: ""
                val loginPhone = coopenBinding.tiedLoginPhone.text.toString()
                coopenBinding.tilLoginPhone.isErrorEnabled = false
                Log.d("getUserLoginPhone", loginPhone)

                if (validatePhone(loginPhone,coopenBinding.tilLoginPhone)){
                    startLogin(loginPhone)
                }
            })
        }

        //顶部Button
        coopenBinding.mbtnLoginTop.also {
            mBtnAlphaTop.observe(this){ mAlpha ->
                Log.d(TAG,"currentAlpha:$mAlpha")
                coopenBinding.btnAlphaTop = mAlpha
                it.isClickable = mAlpha > 0
                coopenBinding.tiedRegisterPhone.isEnabled = mAlpha > 0
                coopenBinding.tiedRegisterUsername.isEnabled = mAlpha > 0
            }

            it.onSafeClick({
//                val registerPhone = mCoopenViewModel.getUserRegisterPhone() ?: ""
//                val registerUsername = mCoopenViewModel.getUserRegisterPhone() ?: ""

                val registerPhone = coopenBinding.tiedRegisterPhone.text.toString()
                val registerUsername = coopenBinding.tiedRegisterUsername.text.toString()

                if (validatePhone(registerPhone,coopenBinding.tilRegisterPhone)){
                    startRegister(registerPhone,registerUsername)
                }
            })
        }

    }

    //收缩状态改变alpha通道
    private fun foldAlpha(mHeight : Int) : Float{
        val tempAlpha = (mHalfScreenHeight  + mArcHeaderHeight- mHeight * 1f) / mHalfScreenHeight
        return max(0f,tempAlpha)
    }

    //拉伸状态改变alpha通道
    private fun unFoldAlpha(mHeight: Int): Float {
        return (mHeight * 1.25f - mHalfScreenHeight ) / mHalfScreenHeight
    }

    //登录
    private fun startLogin(phone : String){
        mCoopenViewModel.loginIn(phone)
        mCoopenViewModel.userToken.observeState(this){
            onSuccess {
                toast("登录成功")
                startActivity(Intent(this@CoopenActivity, MainActivity::class.java))
                MMKV.defaultMMKV().decodeString(Constant.Access_USER_TOKEN,it.token)
                MMKV.defaultMMKV().decodeString(Constant.Refresh_USER_TOKEN,it.refreshToken)
                finish()
            }
            onFailed { _, s ->
                toast(s.toString())
            }
        }
    }

    //注册
    private fun startRegister(phone : String,username : String){
        mCoopenViewModel.register(phone,username)
        mCoopenViewModel.registerInfo.observeState(this){
            onSuccess {
                toast("注册成功")
            }
            onFailed { _, s ->
                toast(s.toString())
            }
        }
    }

    /**
     * 验证登录手机号
     * @param phone
     * @return
     */
    private fun validatePhone(phone: String,til : TextInputLayout): Boolean {
        if (phone.isEmpty()){
            showError(til, "手机号不能为空")
            return false
        }else if (phone.length != 11) {
            showError(til, "手机必须为11位号码")
            return false
        }
        return true
    }

    /**
     * 显示错误提示，并获取焦点
     * @param textInputLayout
     * @param error
     */
    private fun showError(textInputLayout: TextInputLayout, error: String) {
        textInputLayout.error = error
        textInputLayout.editText!!.isFocusable = true
        textInputLayout.editText!!.isFocusableInTouchMode = true
        textInputLayout.editText!!.requestFocus()
    }
}