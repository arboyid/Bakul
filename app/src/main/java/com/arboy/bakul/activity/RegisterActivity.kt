package com.arboy.bakul.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.arboy.bakul.R
import com.arboy.bakul.databinding.ActivityRegisterBinding
import com.arboy.bakul.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(), View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    /*
    * Init view model
    * */
    private lateinit var userViewModel: AuthViewModel
    private var isCheck: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityRegisterBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        userViewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        // binding to xml layout
        binding.authViewModel = userViewModel

        // set status bar color white
        transparentStatusBar()

        initView()
    }

    /*
    * Init event listener component view
    * */
    private fun initView(){

        btnCreateAccount.setOnClickListener(this)
        tvAlreadyAccount.setOnClickListener(this)
        btnCreateAccount.setOnClickListener(this)
        cbTNC.setOnCheckedChangeListener(this)

    }

    override fun onClick(p0: View?) {

        if (p0 == btnCreateAccount){
            // call view model function for register
            userViewModel.registerClick(isCheck, listener)
        }else if (p0 == tvAlreadyAccount){
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

    }

    /*
    * listener when success register
    * */
    private  val listener = object : AuthViewModel.onSuccessListener{
        override fun onSuccess() {
            Intent()
        }
    }

    /*
    * move screen to next page
    * */
    private fun Intent(){
        startActivity(Intent(this, SuccessRegisterActivity::class.java))
        finish()
    }

    /*
    * listener for checkbox
    * */
    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        if (p1){
            isCheck = true
            cbTNC.background = getBackground(R.drawable.bg_checkbox_check)
        }else{
            isCheck = false
            cbTNC.background = getBackground(R.drawable.bg_checkbox)
        }
    }

}