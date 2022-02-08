package com.bignerdranch.android.expensemanager.repository

import androidx.lifecycle.LiveData
import com.bignerdranch.android.expensemanager.data.ExpenseDao
import com.bignerdranch.android.expensemanager.model.Expense

class ExpenseRepository(private val expenseDao: ExpenseDao) {
    val readAllData: LiveData<List<Expense>> = expenseDao.readAllData()

    suspend fun addExpense(expense: Expense) {
        expenseDao.addExpense(expense)
    }

    suspend fun updateExpense(expense: Expense) {
        expenseDao.updateExpense(expense)
    }

    suspend fun deleteSingleExpense(expense: Expense) {
        expenseDao.deleteSingleExpense(expense)
    }

    suspend fun deleteAllExpenses() {
        expenseDao.deleteAllExpenses()
    }
}