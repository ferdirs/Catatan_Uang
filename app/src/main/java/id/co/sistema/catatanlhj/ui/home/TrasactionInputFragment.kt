package id.co.sistema.catatanlhj.ui.home

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import id.co.sistema.catatanlhj.R
import id.co.sistema.catatanlhj.ROOM.Transaction.Transaction
import id.co.sistema.catatanlhj.ROOM.Transaction.TransactionViewModel
import id.co.sistema.catatanlhj.ROOM.UserViewModel
import id.co.sistema.catatanlhj.databinding.FragmentHomeBinding
import id.co.sistema.catatanlhj.databinding.FragmentTrasactionInputBinding


class TrasactionInputFragment : DialogFragment() {

    private var _binding: FragmentTrasactionInputBinding? = null
    private val binding get() = _binding!!
    private lateinit var mTransViewModel: TransactionViewModel
    private lateinit var radio: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mTransViewModel = ViewModelProvider(this).get(TransactionViewModel::class.java)
        _binding = FragmentTrasactionInputBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val radioGroup = binding.rdGroup
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radioButton = binding.root.findViewById<RadioButton>(checkedId)
            radio = radioButton.text.toString()
        }

        binding.btSaveTrans.setOnClickListener {
            insertTrans()
        }
        return root


    }

    private fun insertTrans(){
        if (binding.etNominal.text.isNotEmpty() && binding.etNote.text.isNotEmpty() && radio.isNotEmpty()) {
            val nominal = binding.etNominal.text.toString().toInt()
            val note = binding.etNote.text.toString()


            if (note.isNotEmpty() && nominal.toString().isNotEmpty() && radio.isNotEmpty()) {
                if (radio.equals("Income")) {
                    val trans = Transaction(
                        nominal = nominal,
                        notes = note,
                        jenis = radio,
                        id = 0,
                        income = nominal
                    )
                    mTransViewModel.addTrans(trans)
                    clearText()
                    Log.d("coba", "insertTrans: ${trans.income}")
                    Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val trans = Transaction(
                        nominal = nominal,
                        notes = note,
                        jenis = radio,
                        id = 0,
                        spend = nominal
                    )
                    mTransViewModel.addTrans(trans)
                    clearText()
                    Log.d("coba", "insertTrans: ${trans.spend}")
                    Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(requireContext(), "Check again", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(requireContext(), "Check again", Toast.LENGTH_SHORT).show()

        }
    }


    

    private fun inputCheck(nominal : Int, note: String): Boolean{
        return !(TextUtils.isEmpty(nominal.toString()) && TextUtils.isEmpty(note))
    }

    private fun clearText(){
        binding.apply {
            etNominal.text.clear()
            etNote.text.clear()
        }
    }
}