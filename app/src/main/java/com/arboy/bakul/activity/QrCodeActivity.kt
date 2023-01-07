package com.arboy.bakul.activity

import android.annotation.SuppressLint
import android.os.Bundle
import com.arboy.bakul.R
import com.arboy.bakul.pojo.User
import com.arboy.bakul.util.PreferenceUtil
import com.arboy.bakul.util.SessionManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_qr_code.*
import java.text.DecimalFormat
import java.text.NumberFormat

class QrCodeActivity : BaseActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_code)

        transparentStatusBar()

        // set bacgkround
        ivAvatar2.setImageDrawable(getBackground(R.drawable.avatar))
        ivBarcode.setImageDrawable(getBackground(R.drawable.ic_barcode))
        ivQrcode.setImageDrawable(getBackground(R.drawable.qrcode2))
        ivLogo.setImageDrawable(getBackground(R.drawable.icon_blue))
        ivIde.setImageDrawable(getBackground(R.drawable.idea))

        // auto number formatting
        val formatter: NumberFormat = DecimalFormat("#,###")
        val saldoValue: Int? = PreferenceUtil.getPref(this)?.getInt(PreferenceUtil.SALDO, 0)
        tvIncome.text = "Rp. " +  formatter.format(saldoValue)

        if (SessionManager.getProfile(application) == null) return
        val own: User? = SessionManager.getProfile(application)
        if (own != null) {
            tvFullName.text = own.fullname
        }

    }

}