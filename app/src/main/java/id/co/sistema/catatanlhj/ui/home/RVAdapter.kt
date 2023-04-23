package id.co.sistema.catatanlhj.ui.home

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.co.sistema.catatanlhj.R
import id.co.sistema.catatanlhj.ROOM.Transaction.Transaction
import id.co.sistema.catatanlhj.databinding.ItemTransactionListBinding
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class RVAdapter:RecyclerView.Adapter<RVAdapter.MyViewHolder>()  {

    private var listUserResponse = ArrayList<Transaction>()

    @SuppressLint("NotifyDataSetChanged")
    fun addDataToList(items: List<Transaction>){
        listUserResponse.clear()
        listUserResponse.addAll(items)
        notifyDataSetChanged()
    }

    class MyViewHolder(private var binding: ItemTransactionListBinding): RecyclerView.ViewHolder(binding.root){

        private fun format(number: Int):String{
            val localeID =  Locale("in", "ID")
            val numberFormat = NumberFormat.getCurrencyInstance(localeID)
            return numberFormat.format(number).toString()
        }

        fun bind(transResponse: Transaction){
            binding.inputName.text = transResponse.jenis
            if (transResponse.jenis.equals("Spend")){
                binding.inputNominal.text = "- ${format(transResponse.nominal)}"
                binding.inputNominal.setTextColor(Color.parseColor("#C52121"))
            }else{
                binding.inputNominal.text = format(transResponse.nominal)
                binding.inputNominal.setTextColor(Color.parseColor("#53FF1D"))
            }
            binding.inputNote.text = transResponse.notes
            binding.img.setImageResource(R.drawable.ic_sample_human)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemview = ItemTransactionListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(listUserResponse[position])
    }

    override fun getItemCount(): Int {
        return listUserResponse.size
    }



}