package com.bignerdranch.android.expensemanager.fragments.expensefragments.update

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bignerdranch.android.expensemanager.R
import com.bignerdranch.android.expensemanager.model.Expense
import com.bignerdranch.android.expensemanager.viewmodel.ExpenseViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val updateArgs by navArgs<UpdateFragmentArgs>()

    private lateinit var expenseViewModel: ExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        expenseViewModel = ViewModelProvider(this)[ExpenseViewModel::class.java]

        view.update_expense_name_edit_text.setText(updateArgs.editExpense.name)
        view.update_expense_amount_edit_text.setText(updateArgs.editExpense.amount.toString())
        view.update_expense_description_edit_text.setText(updateArgs.editExpense.description)

        view.update_expense_button.setOnClickListener {
            updateExpense()
        }

        setHasOptionsMenu(true)

        return view
    }

    private fun updateExpense() {
        val expenseName = update_expense_name_edit_text.text.toString()
        val expenseAmount = update_expense_amount_edit_text.text
        val expenseDescription = update_expense_description_edit_text.text.toString()

        if (!checkEmptiness(expenseName, expenseAmount, expenseDescription)) {
            val updatedExpense = Expense(updateArgs.editExpense.id,
                                 expenseName,
                                 Integer.parseInt(expenseAmount.toString()),
                                 expenseDescription)

            expenseViewModel.updateExpense(updatedExpense)

            Toast.makeText(requireContext(), R.string.updated_expense_toast, Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), R.string.empty_cells_toast, Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkEmptiness(name: String, amount: Editable, description: String): Boolean {
        return name.isEmpty() || amount.toString().isEmpty() || description.isEmpty()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_single_expense_item) {
            deleteSingleExpense()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteSingleExpense() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _, ->
            expenseViewModel.deleteSingleExpense(updateArgs.editExpense)
            Toast.makeText(requireContext(), R.string.deleted_expense_toast, Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") {_, _, ->}
        builder.setTitle("Delete single expense")
        builder.setMessage("Are you sure, that you want to delete ${updateArgs.editExpense.name}?")
        builder.create().show()
    }
}