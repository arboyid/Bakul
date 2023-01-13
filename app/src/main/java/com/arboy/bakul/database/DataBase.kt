package com.arboy.bakul.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Wallet::class, Transaction::class], version = 3, exportSchema = false)
abstract class AtomDatabase : RoomDatabase() {

    abstract fun walletDao(): WalletDao

    companion object {
        private var INSTANCE: AtomDatabase? = null

        fun getInstance(context: Context): AtomDatabase? {
            if (INSTANCE == null) {
                synchronized(AtomDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context,
                        AtomDatabase::class.java,
                        "bakul_db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }

}