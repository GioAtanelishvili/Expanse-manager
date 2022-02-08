package com.bignerdranch.android.expensemanager.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bignerdranch.android.expensemanager.model.Expense

@Dao
interface ExpenseDao {
    // Show data in expense amount descending order
    @Query("SELECT * FROM expenses ORDER BY amount DESC")
    fun readAllData(): LiveData<List<Expense>>

    @Query("DELETE FROM expenses")
    suspend fun deleteAllExpenses()

    @Delete
    suspend fun deleteSingleExpense(expense: Expense)

    @Update
    suspend fun updateExpense(expense: Expense)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExpense(expense: Expense)
}