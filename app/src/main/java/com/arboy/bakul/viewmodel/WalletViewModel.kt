package com.arboy.bakul.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.arboy.bakul.database.Wallet
import com.arboy.bakul.repository.WalletRepository

class WalletViewModel(application: Application) : AndroidViewModel(application) {

    private val repository:WalletRepository = WalletRepository(application)
    private val allWalletList: LiveData<List<Wallet>> = repository.getWalletList()

}