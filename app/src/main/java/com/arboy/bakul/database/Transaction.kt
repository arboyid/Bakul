package com.arboy.bakul.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "transaction_tb")
@Parcelize
data class Transaction(@PrimaryKey(autoGenerate = true) val id: Long?,
                       @ColumnInfo(name = "nominal") val nominal: String,
                       @ColumnInfo(name = "type") val type: String,
                       @ColumnInfo(name = "wallet_type") val wallet_type: String,
                       @ColumnInfo(name = "desc") val desc: String
) : Parcelable