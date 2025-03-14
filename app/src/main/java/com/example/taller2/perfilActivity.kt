package com.example.taller2

// Importaciones necesarias para la actividad y gestión de datos
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


// Clase perfilActivity que representa la pantalla de perfil del usuario
class perfilActivity : AppCompatActivity() {
    // Declaración de variables para SharedPreferences y los elementos de la interfaz de usuario
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var txtNombre: TextView
    private lateinit var txtApellido: TextView
    private lateinit var txtTelefono: TextView
    private lateinit var txtEmail: TextView
    private lateinit var editNombre: EditText
    private lateinit var editApellido: EditText
    private lateinit var editTelefono: EditText
    private lateinit var btnEditar: Button
    private lateinit var btnGuardar: Button
    private lateinit var btnCancelar: Button
    private lateinit var botonIngresarFin: Button

    // Método onCreate: se ejecuta cuando la actividad se crea por primera vez
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil) // Establece el diseño de la actividad
        Log.d("CicloVida", "onCreate ejecutado") // Registro en consola del ciclo de vida

        // Inicialización de SharedPreferences para almacenar y recuperar datos del usuario
        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)
        // Inicializa el botón de ingresar a finanzas
        botonIngresarFin = findViewById(R.id.botoningresarfin)

        // Inicialización de los elementos de la interfaz con sus respectivos IDs
        txtNombre = findViewById(R.id.txtNombre)
        txtApellido = findViewById(R.id.txtApellido)
        txtTelefono = findViewById(R.id.txtTelefono)
        txtEmail = findViewById(R.id.txtEmail) // Campo de correo electrónico

        editNombre = findViewById(R.id.editNombre)
        editApellido = findViewById(R.id.editApellido)
        editTelefono = findViewById(R.id.editTelefono)

        btnEditar = findViewById(R.id.btnEditar)
        btnGuardar = findViewById(R.id.btnGuardar)
        btnCancelar = findViewById(R.id.btnCancelar)

        // Cargar los datos guardados en SharedPreferences
        cargarDatos()

        // Configurar el botón de edición para activar el modo de edición
        btnEditar.setOnClickListener {
            activarModoEdicion(true)
        }

        // Configurar el botón de guardado para almacenar los cambios
        btnGuardar.setOnClickListener {
            guardarCambios()
        }

        // Configurar el botón de cancelar para desactivar el modo de edición sin guardar cambios
        btnCancelar.setOnClickListener {
            activarModoEdicion(false)
        }
        // Configurar el listener para el botón
        botonIngresarFin.setOnClickListener {
            // Crear un Intent para iniciar la actividad FinanzasActivity
            val intent = Intent(this, FinanzasActivity::class.java)
            startActivity(intent)  // Iniciar la actividad FinanzasActivity
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

    // Método para cargar datos almacenados en SharedPreferences
    private fun cargarDatos() {
        val nombre = sharedPreferences.getString("nombre", "") ?: ""
        val apellido = sharedPreferences.getString("apellido", "") ?: ""
        val telefono = sharedPreferences.getString("telefono", "") ?: ""
        val email = sharedPreferences.getString("email", "No registrado") ?: "No registrado"

        // Mostrar los datos en los TextView
        txtNombre.text = nombre
        txtApellido.text = apellido
        txtTelefono.text = telefono
        txtEmail.text = email

        // Establecer los valores en los EditText para la edición
        editNombre.setText(nombre)
        editApellido.setText(apellido)
        editTelefono.setText(telefono)

        // Desactivar el modo de edición por defecto
        activarModoEdicion(false)
    }

    // Método para alternar entre el modo de edición y visualización
    private fun activarModoEdicion(editar: Boolean) {
        // Control de visibilidad de los TextView y EditText
        txtNombre.visibility = if (editar) View.GONE else View.VISIBLE
        txtApellido.visibility = if (editar) View.GONE else View.VISIBLE
        txtTelefono.visibility = if (editar) View.GONE else View.VISIBLE
        txtEmail.visibility = View.VISIBLE // Siempre visible

        editNombre.visibility = if (editar) View.VISIBLE else View.GONE
        editApellido.visibility = if (editar) View.VISIBLE else View.GONE
        editTelefono.visibility = if (editar) View.VISIBLE else View.GONE

        // Control de visibilidad de los botones
        btnEditar.visibility = if (editar) View.GONE else View.VISIBLE
        btnGuardar.visibility = if (editar) View.VISIBLE else View.GONE
        btnCancelar.visibility = if (editar) View.VISIBLE else View.GONE
    }

    // Método para guardar los cambios realizados en los campos de edición
    private fun guardarCambios() {
        // Obtener los valores ingresados por el usuario
        val nuevoNombre = editNombre.text.toString().trim()
        val nuevoApellido = editApellido.text.toString().trim()
        val nuevoTelefono = editTelefono.text.toString().trim()

        // Validar que los campos no estén vacíos
        if (nuevoNombre.isEmpty() || nuevoApellido.isEmpty() || nuevoTelefono.isEmpty()) {
            Toast.makeText(this, "Todos los campos deben estar llenos", Toast.LENGTH_SHORT).show()
            return
        }

        // Guardar los datos en SharedPreferences
        sharedPreferences.edit().apply {
            putString("nombre", nuevoNombre)
            putString("apellido", nuevoApellido)
            putString("telefono", nuevoTelefono)
            apply() // Aplicar los cambios
        }

        // Volver a cargar los datos para reflejar los cambios en la interfaz
        cargarDatos()
        Toast.makeText(this, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show()
    }
}
