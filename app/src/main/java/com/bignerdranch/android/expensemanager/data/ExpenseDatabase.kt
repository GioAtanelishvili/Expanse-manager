package com.bignerdranch.android.expensemanager.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bignerdranch.android.expensemanager.model.Expense

@Database(entities = [Expense::class], version = 1, exportSchema = false)
abstract class ExpenseDatabase: RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao

    companion object {
        @Volatile
        private var INSTANCE: ExpenseDatabase? = null

        fun getDatabase(context: Context): ExpenseDatabase {
            val temporaryInstance = INSTANCE

            if (temporaryInstance != null) {
                return temporaryInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                    ExpenseDatabase::class.java,
                    "expense_database")
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}