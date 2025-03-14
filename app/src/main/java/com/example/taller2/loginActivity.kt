package com.example.taller2

// Importación de clases necesarias para la actividad
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

// Definición de la clase loginActivity, que extiende de AppCompatActivity para heredar el comportamiento de una actividad
class loginActivity : AppCompatActivity() {

    // Método onCreate: se ejecuta cuando la actividad se crea por primera vez
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) // Establece el diseño de la actividad desde activity_login.xml
        Log.d("CicloVida", "onCreate ejecutado") // Mensaje de depuración para indicar que onCreate ha sido llamado

        // Obtiene referencias a los botones definidos en el layout XML
        val boton2 = findViewById<Button>(R.id.btnRegisIn) // Botón para ir a la pantalla de registro
        val boton1 = findViewById<Button>(R.id.btnComeIn) // Botón para iniciar sesión

        // Configuración del botón "botoncomenzar"
        boton1.setOnClickListener {
            val intent = Intent(this, SesionActivity::class.java) // Crea una intención para abrir SesionActivity
            startActivity(intent) // Inicia la actividad de sesión
        }

        // Configuración del botón "BotonRegistro"
        boton2.setOnClickListener {
            val intent = Intent(this, registroActivity::class.java) // Crea una intención para abrir registroActivity
            startActivity(intent) // Inicia la actividad de registro
        }
    }

    // Método onStart: se ejecuta cuando la actividad está a punto de hacerse visible
    override fun onStart() {
        super.onStart()
        Log.d("CicloVida", "onStart ejecutado") // Mensaje de depuración
    }

    // Método onResume: se ejecuta cuando la actividad comienza a interactuar con el usuario
    override fun onResume() {
        super.onResume()
        Log.d("CicloVida", "onResume ejecutado") // Mensaje de depuración
    }

    // Método onPause: se ejecuta cuando la actividad entra en segundo plano
    override fun onPause() {
        super.onPause()
        Log.d("CicloVida", "onPause ejecutado") // Mensaje de depuración
    }

    // Método onStop: se ejecuta cuando la actividad ya no es visible para el usuario
    override fun onStop() {
        super.onStop()
        Log.d("CicloVida", "onStop ejecutado") // Mensaje de depuración
    }

    // Método onRestart: se ejecuta cuando la actividad está a punto de reiniciarse después de haber sido detenida
    override fun onRestart() {
        super.onRestart()
        Log.d("CicloVida", "onRestart ejecutado") // Mensaje de depuración
    }
}
