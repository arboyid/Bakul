package com.arboy.bakul.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.arboy.bakul.database.Transaction
import com.arboy.bakul.database.Wallet
import com.arboy.bakul.repository.WalletRepository

class WalletViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WalletRepository = WalletRepository(application)
    private val allWalletList: LiveData<List<Wallet>> = repository.getWalletList()
    private val allWalletActive: LiveData<List<Wallet>> = repository.getWalletActive()
    private val allTransactionList: LiveData<List<Transaction>> = repository.getTransactionList()

    fun saveTodo(wallet: Wallet) {
        repository.saveWallet(wallet)
    }

    fun deleteTodo(wallet: Wallet) {
        repository.deleteWallets(wallet)
    }

    fun udpateWalletStatus(status: Int, wallet: Wallet) {
        repository.updateWalletStatus(status, wallet)
    }

    fun udpateWalletSaldo(saldo: String, wallet: Wallet) {
        repository.updateWalletSaldo(saldo, wallet)
    }

    fun getAllTodoList(): LiveData<List<Wallet>> {
        return allWalletList
    }

    fun getWalletActive(): LiveData<List<Wallet>> {
        return allWalletActive
    }

    fun saveTransaction(transaction: Transaction) {
        repository.saveTransaction(transaction)
    }

    fun getAllTransaction(): LiveData<List<Transaction>> {
        return allTransactionList
    }

    fun deleteTrans(transaction: Transaction) {
        repository.deleteTrans(transaction)
    }

}