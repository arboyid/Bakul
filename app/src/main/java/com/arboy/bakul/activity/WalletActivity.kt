package com.arboy.bakul.activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.arboy.bakul.R
import com.arboy.bakul.adapter.WalletAdapter
import com.arboy.bakul.database.Wallet
import com.arboy.bakul.viewmodel.WalletViewModel
import kotlinx.android.synthetic.main.activity_wallet.*
import kotlinx.android.synthetic.main.toolbar_view.*

class WalletActivity : BaseActivity(), WalletAdapter.walletEvents {

    private lateinit var walletAdapter: WalletAdapter
    private lateinit var walletViewModel: WalletViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)

        // set status bar color white
        transparentStatusBar()
        initView()
    }

    private fun initView(){

        //Setting up RecyclerView
        rvWallet.layoutManager = LinearLayoutManager(this)
        walletAdapter = WalletAdapter(this)
        rvWallet.adapter = walletAdapter

        //Setting up ViewModel and LiveData
        walletViewModel = ViewModelProviders.of(this).get(WalletViewModel::class.java)
        walletViewModel.getAllTodoList().observe(this, Observer {
            walletAdapter.setAllTodoItems(it)
        })

        ivBack.setOnClickListener {
            finish()
        }

        tvTitleWallet.setOnClickListener{
            startActivity(Intent(this, AddWalletActivity::class.java))
        }
    }

    /*
    * listener for detele walled
    * */
    override fun onMenuClicked(wallet: Wallet) {
        walletViewModel.deleteTodo(wallet)
    }

    /*
    * listener for udpate wallet status active or not
    * */
    override fun onSwitchClicked(wallet: Wallet, ischeck: Boolean) {
        if (ischeck){
            walletViewModel.udpateWalletStatus(1, wallet)
        }else{
            walletViewModel.udpateWalletStatus(0, wallet)
        }
    }

}