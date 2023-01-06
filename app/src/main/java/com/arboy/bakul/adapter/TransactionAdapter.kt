package com.arboy.bakul.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.arboy.bakul.R
import com.arboy.bakul.database.Transaction
import kotlinx.android.synthetic.main.item_transaction.view.*
import kotlinx.android.synthetic.main.item_wallet.view.tvDescWallet
import kotlinx.android.synthetic.main.item_wallet.view.tvTitleWallet
import java.text.DecimalFormat
import java.text.NumberFormat

class TransactionAdapter(transaction: transactionEvent) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    private var todoList: List<Transaction> = arrayListOf()
    private var filteredTodoList: List<Transaction> = arrayListOf()
    private val listener: transactionEvent = transaction

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_transaction, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredTodoList[position], listener)

    }

    override fun getItemCount(): Int = filteredTodoList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(transaction: Transaction, listener: transactionEvent) {
            itemView.tvTitleWallet.text = transaction.wallet_type

            val formatter: NumberFormat = DecimalFormat("#,###")
            val saldoValue: Int? = transaction.nominal.toInt()
            itemView.tvDescWallet.text = transaction.type + " " + formatter.format(saldoValue) + " " + "(" + transaction.desc + ")"

            if (transaction.type.equals("Uang Keluar")){
                itemView.tvDescWallet.setTextColor(ContextCompat.getColor(itemView.context, R.color.red_bg))
            }

            itemView.ivDelete.setOnClickListener {
                listener.onDeleteClicked(transaction)
            }

        }

    }

    fun setAllTransItems(transaction: List<Transaction>) {
        this.todoList = transaction
        this.filteredTodoList = transaction
        notifyDataSetChanged()
    }

    interface transactionEvent{
        fun onDeleteClicked(transaction: Transaction)
    }

}