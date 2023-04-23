package id.co.sistema.catatanlhj.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.sistema.catatanlhj.R
import id.co.sistema.catatanlhj.ROOM.Transaction.TransactionViewModel
import id.co.sistema.catatanlhj.databinding.FragmentHomeBinding
import java.text.NumberFormat
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var mTransViewModel: TransactionViewModel


    private val adapter: RVAdapter by lazy {
        RVAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btTrans.setOnClickListener {
            fragmentTransRep()
        }
        homeViewModel.text.observe(viewLifecycleOwner) {

        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideActionBar()
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvMain.layoutManager = layoutManager
        binding.rvMain.adapter = adapter
        mTransViewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)
        mTransViewModel.getAllTrans().observe(viewLifecycleOwner, {names ->
            names.let {
                adapter.addDataToList(names)
            }
        })

        mTransViewModel.getAllIncome().observe(viewLifecycleOwner,{total ->
            if (total!=null){
                binding.inputIncome.text = format(total)
            }else{
                binding.inputIncome.text = "Rp 0"
            }

        })

        mTransViewModel.getAllSpend().observe(viewLifecycleOwner, {total ->
            if (total!=null){
                binding.inputSpend.text = format(total)
            }else{
                binding.inputSpend.text = "Rp 0"
            }
        })

        mTransViewModel.getTotal().observe(viewLifecycleOwner,{total ->
            if (total!=null){
                binding.tvSaldo.text = format(total)
            }else{
                binding.tvSaldo.text = "Rp 0"
            }
        })

        binding.ivDelete.setOnClickListener {
            mTransViewModel.deleteAll()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun hideActionBar() {
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar?.hide()
    }

    private fun fragmentTransRep(){
        val showDialog = TrasactionInputFragment()
        showDialog.show((activity as AppCompatActivity).supportFragmentManager, "showDialog")
    }

    private fun format(number: Int):String{
        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        return numberFormat.format(number).toString()
    }
}