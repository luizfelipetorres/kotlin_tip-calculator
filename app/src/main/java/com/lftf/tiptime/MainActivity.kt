package com.lftf.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lftf.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener {
            val tip = calculateTip()
            displayTip(tip)
        }
    }

    private fun calculateTip(): Double {
        val costString = binding.costOfService.text.toString()
        val cost: Double? = costString.toDoubleOrNull()

        if (cost == null || cost == 0.0) {
            return 0.0
        }

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip: Double = tipPercentage * cost
        if (binding.roundUpSwitch.isChecked)
            tip = ceil(tip)

        return tip
    }

    private fun displayTip(tip: Double) {
        val formatedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formatedTip)
    }
}