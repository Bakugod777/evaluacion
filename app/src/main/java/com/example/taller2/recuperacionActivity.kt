package com.example.taller2

// Importaciones necesarias para la actividad y manejo de datos
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// Clase recuperacionActivity que permite recuperar la contraseña mediante un correo electrónico
class recuperacionActivity : AppCompatActivity() {
    // Declaración de variables para SharedPreferences y elementos de la interfaz
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editTextEmail: EditText
    private lateinit var buttonEnviar: Button

    // Método onCreate: se ejecuta cuando la actividad se crea por primera vez
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recucontra) // Asigna el layout correspondiente

        // Inicialización de SharedPreferences para recuperar datos guardados del usuario
        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)

        // Inicialización de los elementos de la interfaz con sus respectivos IDs
        editTextEmail = findViewById(R.id.etEmRc)
        buttonEnviar = findViewById(R.id.btnESRC)

        // Configuración del botón para enviar la solicitud de recuperación de contraseña
        buttonEnviar.setOnClickListener {
            val email = editTextEmail.text.toString().trim() // Obtiene el texto del campo y elimina espacios extra

            // Verifica la validez del correo y si está registrado
            when {
                !isValidEmail(email) -> { // Si el correo no es válido
                    Log.d("Recuperacion", "Correo inválido ingresado: $email")
                    Toast.makeText(this, "Ingrese un correo válido", Toast.LENGTH_SHORT).show()
                }
                !isEmailRegistered(email) -> { // Si el correo no está registrado
                    Log.d("Recuperacion", "Correo no registrado: $email")
                    Toast.makeText(this, "El correo no está registrado", Toast.LENGTH_SHORT).show()
                }
                else -> { // Si el correo es válido y está registrado
                    Log.d("Recuperacion", "Correo válido y registrado: $email")
                    Toast.makeText(
                        this,
                        "Se envió un correo para la recuperación de la contraseña",
                        Toast.LENGTH_LONG
                    ).show()

                    // Redirige al usuario a la pantalla de inicio de sesión
                    val intent = Intent(this, SesionActivity::class.java)
                    startActivity(intent)
                    finish() // Cierra la actividad actual
                }
            }
        }
    }

    // Método para validar si un correo tiene un formato correcto
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() // Usa una expresión regular predefinida
    }

    // Método para verificar si un correo está registrado en SharedPreferences
    private fun isEmailRegistered(email: String): Boolean {
        val storedEmails = sharedPreferences.all.values.map { it.toString() } // Obtiene todos los valores guardados
        return email in storedEmails // Verifica si el correo ingresado está en la lista de correos registrados
    }

    // Métodos del ciclo de vida de la actividad
    override fun onStart() {
        super.onStart()
        Log.d("CicloVida", "onStart ejecutado")
    }

    override fun onResume() {
        super.onResume()
        Log.d("CicloVida", "onResume ejecutado")
    }

    override fun onPause() {
        super.onPause()
        Log.d("CicloVida", "onPause ejecutado")
    }

    override fun onStop() {
        super.onStop()
        Log.d("CicloVida", "onStop ejecutado")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("CicloVida", "onRestart ejecutado")
    }
}