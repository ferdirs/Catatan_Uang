package id.co.sistema.catatanlhj.ui.dashboard

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import id.co.sistema.catatanlhj.R
import id.co.sistema.catatanlhj.ROOM.Stok.Stock
import id.co.sistema.catatanlhj.ROOM.Stok.StockViewModel
import id.co.sistema.catatanlhj.ROOM.Transaction.TransactionViewModel
import id.co.sistema.catatanlhj.databinding.FragmentStockInputBinding
import id.co.sistema.catatanlhj.databinding.FragmentTrasactionInputBinding


class StockInputFragment : DialogFragment() {

    private var _binding: FragmentStockInputBinding? = null
    private val binding get() = _binding!!
    private lateinit var mStockViewModel: StockViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStockInputBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mStockViewModel = ViewModelProvider(this).get(StockViewModel::class.java)
        binding.btSaveTrans.setOnClickListener {
            insertStock()
        }
    }

    private fun insertStock(){
        if (binding.etNamaBarang.text.isNotEmpty() && binding.etHargaBarang.text.isNotEmpty() && binding.etKuantitas.text.isNotEmpty()) {
            val nama = binding.etNamaBarang.text.toString()
            val harga = binding.etHargaBarang.text.toString().toInt()
            val quan = binding.etKuantitas.text.toString().toInt()

            if (inputCheck(nama, harga, quan)) {
                val stock = Stock(nama_item = nama, harga_item = harga, quantity = quan, id = 0)
                mStockViewModel.addStock(stock)
            } else {
                Toast.makeText(requireContext(), "Check again", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(requireContext(), "Check again", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(nama : String, harga: Int, quan: Int): Boolean{
        return !(TextUtils.isEmpty(nama) && TextUtils.isEmpty(harga.toString()) && TextUtils.isEmpty(quan.toString()))

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StockInputFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}