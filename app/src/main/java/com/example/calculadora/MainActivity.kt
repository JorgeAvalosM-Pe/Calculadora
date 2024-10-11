package com.example.calculadora
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadora.databinding.ActivityMainBinding
//AVALOS MUÑOZ JORGE
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var valorActual = ""
    private var operador = ""
    private var valorAnterior = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setNumberListeners()
        setOperatorListeners()

        binding.btnIgual.setOnClickListener { igualClick() }
        binding.btnLimpiar.setOnClickListener { limpiarClick() }
    }

    private fun setNumberListeners() {
        val numberButtons = listOf(
            binding.btn0, binding.btn1, binding.btn2,
            binding.btn3, binding.btn4, binding.btn5,
            binding.btn6, binding.btn7, binding.btn8, binding.btn9
        )

        for (button in numberButtons) {
            button.setOnClickListener { numeroClick(it) }
        }
    }

    private fun setOperatorListeners() {
        val operatorButtons = listOf(
            binding.btnSuma,
            binding.btnResta,
            binding.btnMultiplicacion,
            binding.btnDivision
        )

        for (button in operatorButtons) {
            button.setOnClickListener { operadorClick(it) }
        }
    }

    private fun numeroClick(view: View) {
        val boton = view as Button
        valorActual += boton.text
        binding.resultadoTextView.text = valorActual
    }

    private fun operadorClick(view: View) {
        val boton = view as Button
        if (valorActual.isNotEmpty()) {
            valorAnterior = valorActual
            operador = boton.text.toString()
            valorActual = ""
        }
    }

    private fun igualClick() {
        if (valorAnterior.isNotEmpty() && valorActual.isNotEmpty()) {
            val resultado = when (operador) {
                "+" -> valorAnterior.toDouble() + valorActual.toDouble()
                "-" -> valorAnterior.toDouble() - valorActual.toDouble()
                "*" -> valorAnterior.toDouble() * valorActual.toDouble()
                "/" -> if (valorActual != "0") valorAnterior.toDouble() / valorActual.toDouble() else "Error"
                else -> 0.0
            }
            binding.resultadoTextView.text = resultado.toString()
            valorActual = ""
            valorAnterior = ""
            operador = ""
        }
    }

    private fun limpiarClick() {
        valorActual = ""
        valorAnterior = ""
        operador = ""
        binding.resultadoTextView.text = "0"
    }
}