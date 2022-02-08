package com.bignerdranch.android.expensemanager.fragments.expensefragments.list

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.expensemanager.R
import com.bignerdranch.android.expensemanager.viewmodel.ExpenseViewModel
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var expenseViewModel: ExpenseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        // RecyclerView
        val adapter = ListAdapter()
        val recyclerView = view.recycler_view
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ExpenseViewModel
        expenseViewModel = ViewModelProvider(this)[ExpenseViewModel::class.java]
        expenseViewModel.readAllData.observe(viewLifecycleOwner, Observer { expense ->
            adapter.setData(expense)
        })

        view.floating_action_button.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        setHasOptionsMenu(true)

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_fragment_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_expanses_item) {
            deleteAllExpenses()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllExpenses() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _, ->
            expenseViewModel.deleteAllExpenses()
            Toast.makeText(requireContext(), R.string.clear_expenses, Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") {_, _, ->}
        builder.setTitle("Delete all expenses")
        builder.setMessage("Are you sure, that you want to delete all the expense data?")
        builder.create().show()
    }
}