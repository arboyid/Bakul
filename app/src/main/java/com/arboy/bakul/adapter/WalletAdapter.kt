package com.arboy.bakul.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arboy.bakul.R
import com.arboy.bakul.database.Wallet
import kotlinx.android.synthetic.main.item_wallet.view.*
import java.text.DecimalFormat
import java.text.NumberFormat


class WalletAdapter(walletEvents: walletEvents) : RecyclerView.Adapter<WalletAdapter.ViewHolder>() {

    private var todoList: List<Wallet> = arrayListOf()
    private var filteredTodoList: List<Wallet> = arrayListOf()
    private val listener: walletEvents = walletEvents

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wallet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filteredTodoList[position], listener)

    }

    override fun getItemCount(): Int = filteredTodoList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(wallet: Wallet, listener: walletEvents) {
            itemView.tvTitleWallet.text = wallet.title
            itemView.tvDescWallet.text = wallet.desc

            try {
                val formatter: NumberFormat = DecimalFormat("#,###")
                val saldoValue: Int? = wallet.saldo.toInt()
                itemView.tvSumWallet.text = "Rp. " + formatter.format(saldoValue)
            }catch (e: NumberFormatException){
                e.printStackTrace()
            }

            itemView.ivMenu.setOnClickListener {
                listener.onMenuClicked(wallet)
            }

            if (wallet.status == 1){
                itemView.swWallet.isChecked = true
            }else{
                itemView.swWallet.isChecked = false
            }

            itemView.swWallet.setOnCheckedChangeListener({ buttonView, isChecked ->
                if (isChecked){
                    itemView.swWallet.isChecked = true
                    listener.onSwitchClicked(wallet,true)
                }else{
                    itemView.swWallet.isChecked = false
                    listener.onSwitchClicked(wallet,false)
                }
            })

        }

    }

    fun setAllTodoItems(todoItems: List<Wallet>) {
        this.todoList = todoItems
        this.filteredTodoList = todoItems
        notifyDataSetChanged()
    }

    interface walletEvents{
        fun onMenuClicked(wallet: Wallet)
        fun onSwitchClicked(wallet: Wallet, ischeck: Boolean)
    }

}