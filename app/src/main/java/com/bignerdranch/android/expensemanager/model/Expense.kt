package com.bignerdranch.android.expensemanager.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.time.temporal.TemporalAmount

@Parcelize
@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val amount: Int,
    val description: String
): Parcelable