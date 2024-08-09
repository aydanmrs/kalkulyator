package com.example.kalkulyator_fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kalkulyator_fragment.databinding.FragmentCalculatorBinding
import com.google.android.material.snackbar.Snackbar

class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCalculatorBinding.bind(view)

        binding.btnAdd.setOnClickListener {
            calculate("+")
        }

        binding.btnMinus.setOnClickListener {
            calculate("-")
        }

        binding.btnMultiply.setOnClickListener {
            calculate("*")
        }

        binding.btnDivide.setOnClickListener {
            calculate("/")
        }
    }

    override fun onResume() {
        super.onResume()
        clearInputFields()
    }

    private fun clearInputFields() {
        binding.edNum1.text.clear()
        binding.edNum2.text.clear()
    }

    private fun calculate(operation: String) {
        val num1 = binding.edNum1.text.toString().toFloatOrNull()
        val num2 = binding.edNum2.text.toString().toFloatOrNull()

        if (num1 == null || num2 == null) {
            showError(getString(R.string.error_numbers_must_be_filled))
            return
        }

        val result: Float? = when (operation) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> {
                if (num2 == 0f) {
                    showError(getString(R.string.division_by_zero_error))
                    return
                } else {
                    num1 / num2
                }
            }

            else -> null
        }

        result?.let {
            val action = CalculatorFragmentDirections.actionCalculatorFragmentToResultFragment(it)
            findNavController().navigate(action)
        }
    }

    private fun showError(message: String) {
        val snackbar = Snackbar.make(binding.root, "", Snackbar.LENGTH_SHORT).apply {
            view.setBackgroundColor(Color.TRANSPARENT)
            (view as ViewGroup).apply {
                removeAllViews()
                layoutInflater.inflate(R.layout.snackbar_layout, this, false).apply {
                    findViewById<TextView>(R.id.snackbar_text).text = message
                    setPadding(0, 80, 0, 80)
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    addView(this)
                }
                layoutParams = (layoutParams as ViewGroup.MarginLayoutParams).apply {
                    setMargins(60, 0, 60, 60)
                    width = ViewGroup.LayoutParams.MATCH_PARENT
                    height = ViewGroup.LayoutParams.WRAP_CONTENT
                }
            }
        }
        snackbar.show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
