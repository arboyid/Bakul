package com.arboy.bakul.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.arboy.bakul.R
import com.arboy.bakul.database.Wallet
import com.arboy.bakul.util.PreferenceUtil
import com.arboy.bakul.viewmodel.WalletViewModel
import kotlinx.android.synthetic.main.activity_add_wallet.*

class AddWalletActivity : BaseActivity() {

    /*
    * Init view model
    * */
    private lateinit var walletViewModel: WalletViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_wallet)

        walletViewModel = ViewModelProvider.of(this).get(WalletViewModel::class.java)

        // set white status bar
        transparentStatusBar()

        btnCreate.setOnClickListener {
            saveWallet()
        }
    }

    /*
    * save wallet to room database and re-count saldo
    * */
    private fun saveWallet(){
        if (validateFields()){
            val getSaldo: String = etSaldo.text.toString()
            val saldoTotal: Int? = PreferenceUtil.getPref(applicationContext)?.getInt(PreferenceUtil.SALDO, 0)?.plus(getSaldo.toInt())
            if (saldoTotal != null) {
                PreferenceUtil.getEditor(applicationContext)?.putInt(PreferenceUtil.SALDO, saldoTotal)?.commit()
            }
            val wallet = Wallet(id = null, title = etTitle.text.toString(), desc = etDesc.text.toString(), saldo = getSaldo, status = 1)
            walletViewModel.saveTodo(wallet)
            finish()
        }
    }

    /**
     * Validation of EditText
     * */
    private fun validateFields(): Boolean {
        if (etTitle.text.isEmpty()) {
            etTitle.error = getString(R.string.pleaseEnterTitle)
            etTitle.requestFocus()
            return false
        }
        if (etDesc.text.isEmpty()) {
            etDesc.error = getString(R.string.pleaseEnterDesc)
            etDesc.requestFocus()
            return false
        }
        if (etSaldo.text.isEmpty()) {
            etSaldo.error = getString(R.string.pleaseEnterSaldo)
            etSaldo.requestFocus()
            return false
        }
        if (etSaldo.text.toString().equals("0")){
            etSaldo.error = getString(R.string.pleaseEnterSaldo)
            etSaldo.requestFocus()
            return false
        }
        return true
    }

}