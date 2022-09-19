package com.lftf.tiptime

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
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

        //Função do botão de calcular
        binding.calculateButton.setOnClickListener {
            val tip = calculateTip()
            displayTip(tip)
        }

        //Esconder teclado ao teclar enter
        binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(
                view,
                keyCode
            )
        }
    }

    private fun calculateTip(): Double {
        val costString = binding.costOfServiceEditText.text.toString()
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

    /**
     * @param view View que será escutada
     * @param keyCode Código da tecla
     *
     * @return verdadeiro se keyCode for teclado
     */
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}