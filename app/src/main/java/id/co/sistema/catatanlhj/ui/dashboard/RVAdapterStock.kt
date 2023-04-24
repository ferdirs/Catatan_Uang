package id.co.sistema.catatanlhj.ui.dashboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.appendPlaceholders
import id.co.sistema.catatanlhj.ROOM.Stok.Stock
import id.co.sistema.catatanlhj.ROOM.Transaction.Transaction
import id.co.sistema.catatanlhj.databinding.ItemRowStockBinding
import id.co.sistema.catatanlhj.databinding.ItemTransactionListBinding
import id.co.sistema.catatanlhj.ui.home.RVAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class RVAdapterStock: RecyclerView.Adapter<RVAdapterStock.MyViewHolder>() {

    private var listStockResponse = ArrayList<Stock>()

    @SuppressLint("NotifyDataSetChanged")
    fun addDataToList(items: List<Stock>){
        listStockResponse.clear()
        listStockResponse.addAll(items)
        notifyDataSetChanged()
    }

    class MyViewHolder(private var binding: ItemRowStockBinding): RecyclerView.ViewHolder(binding.root){
        private fun format(number: Int):String{
            val localeID =  Locale("in", "ID")
            val numberFormat = NumberFormat.getCurrencyInstance(localeID)
            return numberFormat.format(number).toString()
        }

        fun bind(stock: Stock){
            binding.tvItemName.text = stock.nama_item
            binding.tvItemPrice.text = format(stock.harga_item)
            binding.tvItemQuantity.text = "${stock.quantity} Kg"

            binding.rowLayout.setOnClickListener {
                val action = DashboardFragmentDirections.actionNavigationDashboardToUpdateStockFragment(stock)
                this.itemView.findNavController().navigate(action)
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemview = ItemRowStockBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RVAdapterStock.MyViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listStockResponse[position])

    }

    override fun getItemCount(): Int {
        return listStockResponse.size
    }



}