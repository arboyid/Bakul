package com.arboy.bakul.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.arboy.bakul.database.AtomDatabase
import com.arboy.bakul.database.Transaction
import com.arboy.bakul.database.Wallet
import com.arboy.bakul.database.WalletDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class WalletRepository(application: Application) {

    private val walletDao: WalletDao
    private val allWallets: LiveData<List<Wallet>>
    private val allWalletsWhereStatus: LiveData<List<Wallet>>
    private val allTransaction: LiveData<List<Transaction>>

    init {
        val database = AtomDatabase.getInstance(application.applicationContext)
        walletDao = database!!.walletDao()
        allTransaction = walletDao.getAllTransaction()
        allWallets = walletDao.getAllTodoList()
        allWalletsWhereStatus = walletDao.getWalletActive("1")
    }

    fun saveWallet(wallet: Wallet) = runBlocking {
        this.launch(Dispatchers.IO) {
            walletDao.saveWallet(wallet)
        }
    }

    fun deleteWallets(wallet: Wallet) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                walletDao.deleteWallet(wallet)
            }
        }
    }

    fun updateWalletStatus(status: Int, wallet: Wallet) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                wallet.id?.let { walletDao.updateWalletStatus(status, it) }
            }
        }
    }

    fun updateWalletSaldo(saldo: String, wallet: Wallet) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                wallet.id?.let { walletDao.updateWalletSaldo(saldo, it) }
            }
        }
    }

    fun getWalletList(): LiveData<List<Wallet>> {
        return allWallets
    }

    fun getWalletActive(): LiveData<List<Wallet>> {
        return allWalletsWhereStatus
    }

    fun saveTransaction(transaction: Transaction) = runBlocking {
        this.launch(Dispatchers.IO) {
            walletDao.saveTransaction(transaction)
        }
    }

    fun getTransactionList(): LiveData<List<Transaction>> {
        return allTransaction
    }

    fun deleteTrans(transaction: Transaction) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                walletDao.deleteTrans(transaction)
            }
        }
    }

}