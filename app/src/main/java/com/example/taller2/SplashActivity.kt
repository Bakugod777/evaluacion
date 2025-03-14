    package com.example.taller2
    // Importación de clases necesarias para la actividad y manejo de navegación
    import android.os.Bundle
    import androidx.appcompat.app.AppCompatActivity
    import android.content.Intent
    import android.os.Handler
    import android.os.Looper
    import android.util.Log
    // Clase SplashActivity que muestra una pantalla de presentación antes de redirigir al login
    class SplashActivity: AppCompatActivity() {

        // Tiempo de duración del splash en milisegundos (3 segundos)
        private val splashTimeOut: Long = 3000

        // Método onCreate: se ejecuta cuando la actividad se crea por primera vez
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_splash) // Asigna el diseño de la actividad

            // Configura un temporizador para mostrar la pantalla de bienvenida durante "splashTimeOut"
            Handler(Looper.getMainLooper()).postDelayed({
                // Crea una intención para redirigir a la pantalla de inicio de sesión
                val intent = Intent(this, loginActivity::class.java)
                startActivity(intent) // Inicia la actividad de login
                finish() // Finaliza SplashActivity para que el usuario no pueda regresar a ella con el botón "Atrás"
            }, splashTimeOut)
        }

        // Métodos del ciclo de vida de la actividad para seguimiento con Log.d
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