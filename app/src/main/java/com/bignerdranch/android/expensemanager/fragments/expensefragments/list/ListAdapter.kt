package com.bignerdranch.android.expensemanager.fragments.expensefragments.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.expensemanager.R
import com.bignerdranch.android.expensemanager.model.Expense
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var expenseList = emptyList<Expense>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = expenseList[position]

        holder.itemView.list_item_name_edit_text.text = "Name: ${currentItem.name}"
        holder.itemView.list_item_amount_edit_text.text = "Amount: ${currentItem.amount} GEL"
        holder.itemView.list_item_description_edit_text.text = "Description: ${currentItem.description}"

        holder.itemView.list_item_root.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
//            holder.itemView.findNavController().navigate(action)
            holder.itemView.findNavController().navigate(action)
        }
    }

    fun setData(expense: List<Expense>) {
        this.expenseList = expense
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }
}