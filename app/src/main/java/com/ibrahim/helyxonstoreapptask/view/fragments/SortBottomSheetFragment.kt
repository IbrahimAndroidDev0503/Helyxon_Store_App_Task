package com.ibrahim.helyxonstoreapptask.view.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ibrahim.helyxonstoreapptask.R
import com.ibrahim.helyxonstoreapptask.databinding.FragmentSortBottomSheetBinding

class SortBottomSheetFragment : BottomSheetDialogFragment() {

    private var onSortSelected: ((String) -> Unit)? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sort_bottom_sheet, container, false)

        val sharedPreferences = requireContext().getSharedPreferences("prefs", AppCompatActivity.MODE_PRIVATE)
        val savedSortOrder = sharedPreferences.getString("SORT_ORDER", "Low to High") ?: "Low to High"

        val radioLowToHigh = view.findViewById<RadioButton>(R.id.lowRB)
        val radioHighToLow = view.findViewById<RadioButton>(R.id.highRB)

        if (savedSortOrder == "Low to High") {
            radioLowToHigh.isChecked = true
        } else {
            radioHighToLow.isChecked = true
        }

        view.findViewById<RadioGroup>(R.id.radioGroup).setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.lowRB -> onSortSelected?.invoke("Low to High")
                R.id.highRB -> onSortSelected?.invoke("High to Low")
            }
            dismiss()
        }

        return view
    }

    companion object {
        fun show(fragmentManager: FragmentManager, onSortSelected: (String) -> Unit) {
            val fragment = SortBottomSheetFragment()
            fragment.onSortSelected = onSortSelected
            fragment.show(fragmentManager, SortBottomSheetFragment::class.java.simpleName)
        }
    }
}

