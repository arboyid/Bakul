package com.arboy.bakul.database

@Dao
interface WalletDao {

    @Insert
    suspend fun saveWallet

}