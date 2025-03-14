package com.example.taller2

// Importación de las clases necesarias para la actividad y la gestión de datos
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

// Clase registroActivity: permite el registro de un nuevo usuario
class registroActivity : AppCompatActivity() {
    // Declaración de SharedPreferences para almacenar los datos del usuario
    private lateinit var sharedPreferences: SharedPreferences

    // Método onCreate: se ejecuta cuando la actividad se crea por primera vez
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro) // Asigna el diseño de la actividad

        // Inicialización de SharedPreferences para guardar datos de usuario
        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)

        // Obtención de referencias a los elementos de la interfaz
        val editNombre = findViewById<EditText>(R.id.editNombre)
        val editApellido = findViewById<EditText>(R.id.editApellido)
        val editEmail = findViewById<EditText>(R.id.etEmRc)
        val editTelefono = findViewById<EditText>(R.id.editTelefono)
        val editPassword = findViewById<EditText>(R.id.editPassword)
        val editPasswordConfirm = findViewById<EditText>(R.id.editPasswordConfirm)
        val checkTerms = findViewById<CheckBox>(R.id.checkTerms)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        val textLoginRedirect = findViewById<TextView>(R.id.textLoginRedirect)

        // Configuración del botón de registro
        btnRegister.setOnClickListener {
            val nombre = editNombre.text.toString().trim()
            val apellido = editApellido.text.toString().trim()
            val email = editEmail.text.toString().trim()
            val telefono = editTelefono.text.toString().trim()
            val password = editPassword.text.toString()
            val passwordConfirm = editPasswordConfirm.text.toString()

            // Validaciones antes de registrar al usuario
            if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || telefono.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
                Toast.makeText(this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != passwordConfirm) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!checkTerms.isChecked) {
                Toast.makeText(this, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Guardar datos en SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("nombre", nombre)
            editor.putString("apellido", apellido)
            editor.putString("email", email)
            editor.putString("telefono", telefono)
            editor.putString("password", password)
            editor.apply() // Aplica los cambios

            // Mostrar mensaje de confirmación
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()

            // Redirigir a la pantalla de inicio de sesión
            val intent = Intent(this, SesionActivity::class.java)
            startActivity(intent)
            finish() // Finaliza la actividad actual para evitar que el usuario regrese con "atrás"
        }

        // Configuración del texto que redirige a la pantalla de inicio de sesión
        textLoginRedirect.setOnClickListener {
            val intent = Intent(this, loginActivity::class.java)
            startActivity(intent)
            finish() // Finaliza la actividad para evitar que el usuario regrese con "atrás"
        }
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
