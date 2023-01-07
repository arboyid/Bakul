package com.arboy.bakul.activity

import android.content.Intent
import android.os.Bundle
import com.arboy.bakul.R
import kotlinx.android.synthetic.main.activity_succes_register.*

//import kotlinx.android.synthetic.main.activity_success_register

class SuccessRegisterActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_succes_register)

        transparentStatusBar()

        ivThumbsUp.setImageDrawable(getBackground(R.drawable.ic_jempul))
        btnToMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

}