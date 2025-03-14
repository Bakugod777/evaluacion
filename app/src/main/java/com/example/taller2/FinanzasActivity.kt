package com.example.taller2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import java.text.SimpleDateFormat
import java.util.*

class FinanzasActivity : AppCompatActivity() {

    private lateinit var edtIngresos: EditText
    private lateinit var edtGastosFijos: EditText
    private lateinit var edtGastosVariables: EditText
    private lateinit var edtDeudas: EditText
    private lateinit var btnCalcular: Button
    private lateinit var tvResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finanzas)

        // Inicialización de vistas
        edtIngresos = findViewById(R.id.edtIngresos)
        edtGastosFijos = findViewById(R.id.edtGastosFijos)
        edtGastosVariables = findViewById(R.id.edtGastosVariables)
        edtDeudas = findViewById(R.id.edtDeudas)
        btnCalcular = findViewById(R.id.btnCalcular)
        tvResultado = findViewById(R.id.tvResultado)

        // Configurar el botón de cálculo
        btnCalcular.setOnClickListener {
            calcularFinanzas()
        }
    }

    private fun calcularFinanzas() {
        val ingresosStr = edtIngresos.text.toString()
        val gastosFijosStr = edtGastosFijos.text.toString()
        val gastosVariablesStr = edtGastosVariables.text.toString()
        val deudasStr = edtDeudas.text.toString()

        // Validación de entradas
        if (ingresosStr.isEmpty() || gastosFijosStr.isEmpty() || gastosVariablesStr.isEmpty() || deudasStr.isEmpty()) {
            Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val ingresos = ingresosStr.toDouble()
            val gastosFijos = gastosFijosStr.toDouble()
            val gastosVariables = gastosVariablesStr.toDouble()
            val deudas = deudasStr.toDouble()

            // Realizar los cálculos
            val presupuestoDisponible = ingresos - (gastosFijos + gastosVariables)
            val capacidadAhorro = presupuestoDisponible * 0.20
            val indiceEndeudamiento = (deudas / ingresos) * 100

            // Mostrar resultados
            tvResultado.text =
                """
                Presupuesto Disponible: $presupuestoDisponible
                Capacidad de Ahorro: $capacidadAhorro
                Índice de Endeudamiento: $indiceEndeudamiento%
            """

            // Guardar los resultados en SharedPreferences
            guardarResultados(ingresos, gastosFijos, gastosVariables, deudas, presupuestoDisponible, capacidadAhorro, indiceEndeudamiento)

        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Por favor ingrese valores numéricos válidos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun guardarResultados(ingresos: Double, gastosFijos: Double, gastosVariables: Double, deudas: Double, presupuestoDisponible: Double, capacidadAhorro: Double, indiceEndeudamiento: Double) {
        val sharedPreferences = getSharedPreferences("finanzas", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val fecha = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        editor.putString("fecha", fecha)
        editor.putString("presupuesto", presupuestoDisponible.toString())
        editor.putString("ahorro", capacidadAhorro.toString())
        editor.putString("endeudamiento", indiceEndeudamiento.toString())
        editor.apply()
    }
}