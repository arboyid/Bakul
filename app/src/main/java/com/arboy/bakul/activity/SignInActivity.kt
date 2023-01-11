package com.arboy.bakul.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.arboy.bakul.R
import com.arboy.bakul.databinding.ActivitySignInBinding
import com.arboy.bakul.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : BaseActivity(), View.OnClickListener {

    /*
    * Init view model
    * */
    private lateinit var userViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivitySignInBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)
        userViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        // binding for screen xml
        binding.authViewModel = userViewModel

        // set color status bar white
        transparentStatusBar()

        // init view
        initView()
    }

    private fun initView(){

        tvForgotPassword.setOnClickListener(this)
        btnSignIn.setOnClickListener(this)
        tvCreateAccount.setOnClickListener(this)
    }

    /*
    * listener for success login
    * */
    private  val listener = object : AuthViewModel.onSuccessListener{
        override fun onSuccess() {
            Intent()
        }
    }

    /*
    * move screen to the next page
    * */
    private fun Intent(){
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    /*
    * onclick event
    * */
    override fun onClick(p0: View?) {
        when (p0) {
            tvForgotPassword -> {
                showToast("Fitur sedang dalam pengembangan")
            }
            btnSignIn -> {
                userViewModel.loginClick(listener)
            }
            tvCreateAccount -> {
                startActivity(Intent(this, RegisterActivity::class.java))
                finish()
            }
        }
    }
}