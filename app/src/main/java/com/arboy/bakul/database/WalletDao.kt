package com.arboy.bakul.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WalletDao {

    @Insert
    suspend fun saveWallet(wallet: Wallet)

    @Delete
    suspend fun deleteWallet(wallet: Wallet)

    @Delete
    suspend fun deleteTrans(transaction: Transaction)

    @Query("SELECT * FROM wallet_tb ORDER BY id DESC")
    fun getAllTodoList(): LiveData<List<Wallet>>

    @Query("SELECT * FROM wallet_tb WHERE status = :status")
    fun getWalletActive(status: String): LiveData<List<Wallet>>

    @Insert
    suspend fun saveTransaction(transaction: Transaction)

    @Query("SELECT * FROM transaction_tb ORDER BY id DESC")
    fun getAllTransaction(): LiveData<List<Transaction>>

    @Query("UPDATE wallet_tb SET saldo=:saldo WHERE id = :id")
    fun updateWalletSaldo(saldo: String, id: Long)

    @Query("UPDATE wallet_tb SET status=:status WHERE id = :id")
    fun updateWalletStatus(status: Int, id: Long)
}