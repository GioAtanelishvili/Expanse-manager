package com.bignerdranch.android.expensemanager.fragments.expensefragments.add

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.expensemanager.R
import com.bignerdranch.android.expensemanager.model.Expense
import com.bignerdranch.android.expensemanager.viewmodel.ExpenseViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var expenseViewModel: ExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        expenseViewModel = ViewModelProvider(this)[ExpenseViewModel::class.java]

        view.add_expense_button.setOnClickListener {
            createNewExpanse()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun createNewExpanse() {
        val expenseName = expense_name_edit_text.text.toString()
        val expenseAmount = expense_amount_edit_text.text
        val expenseDescription = expense_description_edit_text.text.toString()

        if (!checkEmptiness(expenseName, expenseAmount, expenseDescription)) {
            val expense = Expense(0, expenseName, Integer.parseInt(expenseAmount.toString()), expenseDescription)

            expenseViewModel.addExpense(expense)

            Toast.makeText(requireContext(), R.string.expense_added_toast, Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), R.string.empty_cells_toast, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkEmptiness(name: String, amount: Editable, description: String): Boolean {
        return name.isEmpty() || amount.toString().isEmpty() || description.isEmpty()
    }
}