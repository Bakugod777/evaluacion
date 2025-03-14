package com.example.taller2

// Importación de las librerías necesarias para la actividad y el manejo de la UI
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// Definición de la clase MainActivity, que hereda de AppCompatActivity
class MainActivity : AppCompatActivity() {
    // Método onCreate: se ejecuta cuando la actividad se crea por primera vez
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Habilita el modo edge-to-edge (pantalla completa sin bordes) en la aplicación
        enableEdgeToEdge()

        // Establece el diseño de la actividad desde activity_main.xml
        setContentView(R.layout.activity_main)

        // Ajusta los márgenes de la vista con ID "Nombreapp" para respetar los insets del sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Nombreapp)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()) // Obtiene los márgenes del sistema (barra de estado, navegación)

            // Aplica los márgenes obtenidos a la vista "Nombreapp" para que no se superponga con la barra de estado o navegación
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)

            insets // Devuelve los insets aplicados
        }
    }
}