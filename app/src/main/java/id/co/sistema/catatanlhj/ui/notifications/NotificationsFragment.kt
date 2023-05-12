package id.co.sistema.catatanlhj.ui.notifications

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import id.co.sistema.catatanlhj.R
import id.co.sistema.catatanlhj.ROOM.Transaction.Transaction
import id.co.sistema.catatanlhj.ROOM.Transaction.TransactionViewModel
import id.co.sistema.catatanlhj.databinding.FragmentNotificationsBinding
import kotlin.math.log
import kotlin.properties.Delegates

class NotificationsFragment : Fragment() {

    private lateinit var mTransViewModel: TransactionViewModel

    private var income: Float = 0f
    private var spend: Float = 0f

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mTransViewModel = ViewModelProvider(this@NotificationsFragment).get(TransactionViewModel::class.java)
        mTransViewModel.countIncome().observe(viewLifecycleOwner) {
            income = it.toFloat()
        }
        mTransViewModel.countSpend().observe(viewLifecycleOwner) {
            spend = it.toFloat()
            pieChart()
        }

        mTransViewModel.countTrans().observe(viewLifecycleOwner) {
            binding.tvTotaltrans.text = it.toString()
        }
        binding.tvJudul.text = "Pie Chart Dari Seluruh Transaksi Hari ini"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun pieChart(){
        binding.apply {  pieChart.description.isEnabled = false
            pieChart.setExtraOffsets(5f, 10f, 5f, 5f)
            pieChart.dragDecelerationFrictionCoef = 0.95f
            pieChart.isDrawHoleEnabled = true
            pieChart.setHoleColor(Color.WHITE)

            pieChart.setTransparentCircleColor(Color.WHITE)
            pieChart.setTransparentCircleAlpha(110)

            pieChart.setHoleRadius(58f)
            pieChart.setTransparentCircleRadius(61f)

            pieChart.setDrawCenterText(true)

            pieChart.setRotationAngle(0f)

            pieChart.setRotationEnabled(true)
            pieChart.setHighlightPerTapEnabled(true)

            pieChart.animateY(1400, Easing.EaseInOutQuad)


            pieChart.legend.isEnabled = false
            pieChart.setEntryLabelColor(Color.WHITE)
            pieChart.setEntryLabelTextSize(12f)


            // on below line we are creating array list and
            // adding data to it to display in pie chart
            val entries: ArrayList<PieEntry> = ArrayList()
            entries.add(PieEntry(income))
            entries.add(PieEntry(spend))
            val dataSet = PieDataSet(entries, "Mobile OS")

            // on below line we are setting icons.
            dataSet.setDrawIcons(false)

            // on below line we are setting slice for pie
            dataSet.sliceSpace = 3f
            dataSet.iconsOffset = MPPointF(0f, 40f)
            dataSet.selectionShift = 5f

            val colors: ArrayList<Int> = ArrayList()
            colors.add(resources.getColor(R.color.green))
            colors.add(resources.getColor(R.color.red))

            dataSet.colors = colors
            // on below line we are setting pie data set
            val data = PieData(dataSet)
            data.setValueFormatter(PercentFormatter())
            data.setValueTextSize(15f)
            data.setValueTypeface(Typeface.DEFAULT_BOLD)
            data.setValueTextColor(Color.WHITE)
            pieChart.setData(data)
            // undo all highlights
            pieChart.highlightValues(null)
            // loading chart
            pieChart.invalidate()

        }
    }

}