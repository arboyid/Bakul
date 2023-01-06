package com.arboy.bakul.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.arboy.bakul.R
import com.arboy.bakul.util.AppConstant
import com.arboy.bakul.util.PreferenceUtil
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // set status bar color blue
        transparentStatusBar()
        imgLogo.setImageDrawable(getBackground(R.drawable.atomic_logo))

        // delay for to next activity
        Handler().postDelayed({ toActivity() }, AppConstant.LENGTH_SPLASH)

    }

    /*
    * state for choose actvity want to run base on preference
    * */
    private fun toActivity(){

        val isRegisterDone: Boolean? = PreferenceUtil.getPref(this)?.getBoolean(PreferenceUtil.SETUP_REGISTER, false)
        val isLoginDone: Boolean? = PreferenceUtil.getPref(this)?.getBoolean(PreferenceUtil.SETUP_LOGIN, false)

        if (isRegisterDone == true && isLoginDone == false){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else if (isRegisterDone == false && isLoginDone == true){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else if (isRegisterDone == true && isLoginDone == true){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }


    }

}