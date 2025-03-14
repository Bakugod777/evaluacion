package com.example.taller2

// Importación de clases necesarias para la actividad y la gestión de datos
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

// Clase SesionActivity que gestiona el inicio de sesión del usuario

class SesionActivity : AppCompatActivity() {
    // Declaración de variables para SharedPreferences y elementos de la interfaz de usuario
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var botonIngresar: Button
    private lateinit var botonRegistro: Button
    private lateinit var botonRecupera: Button

    // Método onCreate: se ejecuta cuando la actividad se crea por primera vez
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sesion) // Asigna el diseño de la actividad

        // Inicialización de SharedPreferences para recuperar los datos guardados del usuario
        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)

        // Inicialización de los elementos de la interfaz con sus respectivos IDs
        editTextEmail = findViewById(R.id.editTextUsuario)
        editTextPassword = findViewById(R.id.editTextContraseña)
        botonIngresar = findViewById(R.id.botoningresar)
        botonRegistro = findViewById(R.id.botonregistro)
        botonRecupera = findViewById(R.id.botonrecupera)

        // Configuración del botón de inicio de sesión
        botonIngresar.setOnClickListener {
            val email = editTextEmail.text.toString().trim() // Obtiene el email ingresado
            val password = editTextPassword.text.toString() // Obtiene la contraseña ingresada

            // Validación del correo electrónico
            if (!isValidEmail(email)) {
                Toast.makeText(this, "Ingrese un correo válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Verificación de las credenciales en SharedPreferences
            if (isValidUser(email, password)) {
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()

                // Redirigir a la actividad de perfil tras un inicio de sesión exitoso
                val intent = Intent(this, perfilActivity::class.java)
                startActivity(intent)
                finish() // Finaliza la actividad para que no se pueda volver atrás con el botón "Atrás"
            } else {
                Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }

        // Configuración del botón para redirigir a la pantalla de registro
        botonRegistro.setOnClickListener {
            val intent = Intent(this, registroActivity::class.java)
            startActivity(intent)
        }

        // Configuración del botón para redirigir a la pantalla de recuperación de contraseña
        botonRecupera.setOnClickListener {
            val intent = Intent(this, recuperacionActivity::class.java)
            startActivity(intent)
        }
    }

    // Método para validar que el formato del correo electrónico sea correcto
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches() // Usa una expresión regular predefinida
    }

    // Método para verificar si las credenciales ingresadas coinciden con las almacenadas en SharedPreferences
    private fun isValidUser(email: String, password: String): Boolean {
        val storedEmail = sharedPreferences.getString("email", null) // Recupera el email guardado
        val storedPassword = sharedPreferences.getString("password", null) // Recupera la contraseña guardada
        return storedEmail == email && storedPassword == password // Compara los datos ingresados con los guardados
    }

    // Métodos del ciclo de vida de la actividad para seguimiento en consola con Log.d
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
