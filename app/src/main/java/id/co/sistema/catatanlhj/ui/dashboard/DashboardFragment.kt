package id.co.sistema.catatanlhj.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.sistema.catatanlhj.ROOM.Stok.StockViewModel
import id.co.sistema.catatanlhj.ROOM.Transaction.TransactionViewModel
import id.co.sistema.catatanlhj.databinding.FragmentDashboardBinding
import id.co.sistema.catatanlhj.ui.home.RVAdapter
import id.co.sistema.catatanlhj.ui.home.TrasactionInputFragment

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var mStockViewModel: StockViewModel

    private val adapter: RVAdapterStock by lazy {
        RVAdapterStock()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.btStock.setOnClickListener{
            fragmentStockRep()
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mStockViewModel = ViewModelProvider(this).get(StockViewModel::class.java)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rvMainStock.layoutManager = layoutManager
        binding.rvMainStock.adapter = adapter
        mStockViewModel.getAllStock().observe(viewLifecycleOwner,{stock->
            adapter.addDataToList(stock)
        })

        setUpSearchView()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fragmentStockRep(){
        val showDialog = StockInputFragment()
        showDialog.show((activity as AppCompatActivity).supportFragmentManager, "showDialog")
    }

    private fun setUpSearchView() {

        with(binding) {
            searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    if (query!=null){
                        searchDatabase(query)
                    }
                    return true
                }
                override fun onQueryTextChange(p0: String?): Boolean {
                    if (p0!=null){
                        searchDatabase(p0)
                    }
                    return true
                }

            })

        }
    }

    private fun searchDatabase(query: String){
        val searchQuery = "%$query%"
        mStockViewModel.searchDatabase(searchQuery).observe(this, {
            it.let {
                adapter.addDataToList(it)
            }
        })
    }
}