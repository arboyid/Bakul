package com.arboy.bakul.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.arboy.bakul.R
import com.arboy.bakul.database.Wallet
import java.text.DecimalFormat
import java.text.NumberFormat

class SpinnerAdapter(val context: Context, var dataSource: List<Wallet>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    @SuppressLint("SetTextI18n")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val vh: ItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.item_spinner, parent, false)
            vh = ItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemHolder
        }

        try {
            val formatter: NumberFormat = DecimalFormat("#,###")
            val saldoValue: Int? = dataSource.get(position).saldo.toInt()
            vh.label.text = dataSource.get(position).title +  " Rp. " + formatter.format(saldoValue)
        }catch (e: NumberFormatException){
            e.printStackTrace()
        }

        return view
    }

    override fun getItem(position: Int): Any? {
        return dataSource[position];
    }

    override fun getCount(): Int {
        return dataSource.size;
    }

    override fun getItemId(position: Int): Long {
        return position.toLong();
    }

    private class ItemHolder(row: View?) {
        val label: TextView

        init {
            label = row?.findViewById(R.id.tvTitle) as TextView
        }
    }

}