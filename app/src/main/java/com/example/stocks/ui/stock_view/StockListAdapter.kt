package com.example.stocks.ui.stock_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stocks.databinding.ItemHoldingBinding
import com.example.stocks.ui.model.Stock

class StockListAdapter(
     var data: List<Stock>,
) : RecyclerView.Adapter<StockListAdapter.StockViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val itemBinding = ItemHoldingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StockViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        val content = data[position]
        holder.bind(content)
    }



    override fun getItemCount(): Int = data.size

    class StockViewHolder(private val binding: ItemHoldingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(stock: Stock) {
            binding.tvStockSymbol.text = stock.ticker
            binding.tvCompanyName.text = stock.name
            binding.tvCurrentPrice.text = "Current Price: $${stock.price}"
            binding.tvChange.text = "Change: ${stock.day_change}%"

            binding.tvTrendIndicator.text = if (stock.day_change >= 0) "\uD83D\uDCB9" else "📉"
        }
    }
}