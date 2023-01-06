package com.arboy.bakul.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "wallet_tb")
@Parcelize
data class Wallet(@PrimaryKey(autoGenerate = true) val id: Long?,
                  @ColumnInfo(name = "title") val title: String,
                  @ColumnInfo(name = "desc") val desc: String,
                  @ColumnInfo(name = "saldo") val saldo: String,
                  @ColumnInfo(name = "status") val status: Int
) : Parcelable