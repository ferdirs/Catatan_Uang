package id.co.sistema.catatanlhj.ui.dashboard

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import id.co.sistema.catatanlhj.R
import id.co.sistema.catatanlhj.ROOM.Stok.Stock
import id.co.sistema.catatanlhj.ROOM.Stok.StockViewModel
import id.co.sistema.catatanlhj.databinding.FragmentTrasactionInputBinding
import id.co.sistema.catatanlhj.databinding.FragmentUpdateStockBinding


class UpdateStockFragment : Fragment() {

    private val args by navArgs<UpdateStockFragmentArgs>()
    private var _binding: FragmentUpdateStockBinding? = null
    private val binding get() = _binding!!
    private lateinit var mStockViewModel: StockViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateStockBinding.inflate(inflater, container, false)
        val root: View = binding.root

        mStockViewModel = ViewModelProvider(this).get(StockViewModel::class.java)

        binding.etNamaBarang.setText(args.currentStock.nama_item)
        binding.etHargaBarang.setText(args.currentStock.harga_item.toString())
        binding.etKuantitas.setText(args.currentStock.quantity.toString())

        binding.btUpdateStock.setOnClickListener { updateItem() }

        binding.btDeleteStock.setOnClickListener { deleteUser() }
       return root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun updateItem(){
        val namaBarang = binding.etNamaBarang.text.toString()
        val hargaBarang = binding.etHargaBarang.text.toString().toInt()
        val kuantitas = binding.etKuantitas.text.toString().toInt()

        if (inputCheck(namaBarang, hargaBarang, kuantitas)){
            val updateStock = Stock(args.currentStock.id, namaBarang,hargaBarang,kuantitas)
            mStockViewModel.updateStock(updateStock)
            findNavController().navigate(R.id.action_updateStockFragment_to_navigation_dashboard)
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(), "Mohon Di isi!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun inputCheck(nama : String, harga: Int, quan: Int): Boolean{
        return !(TextUtils.isEmpty(nama) && TextUtils.isEmpty(harga.toString()) && TextUtils.isEmpty(quan.toString()))

    }


    private fun deleteUser(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_,_ ->
            mStockViewModel.deleteStock(args.currentStock)
            Toast.makeText(requireContext(), "Successfully removed: ${args.currentStock.nama_item}!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateStockFragment_to_navigation_dashboard)
        }
        builder.setNegativeButton("No"){_,_ ->

        }
        builder.setTitle("Delete ${args.currentStock.nama_item}?")
        builder.setMessage("Are You Sure want to delete ${args.currentStock.nama_item}")
        builder.create().show()

    }

}