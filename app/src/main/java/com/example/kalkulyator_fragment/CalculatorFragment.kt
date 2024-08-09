package com.example.kalkulyator_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kalkulyator_fragment.databinding.FragmentCalculatorBinding

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

    private fun calculate(operation: String) {
        val num1 = binding.edNum1.text.toString().toFloatOrNull()
        val num2 = binding.edNum2.text.toString().toFloatOrNull()

        if (num1 == null || num2 == null) {
            return
        }


        val result: Float? = when (operation) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> num1 / num2

            else -> null
        }
    }
}
