package mx.edu.tecmm.moviles.agenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun agregar(v: View){
            Log.e("boton","agregar")
        val intent = Intent(this,agregar::class.java)
        startActivity(intent)
    }

    fun Consultar(v: View){
        Log.e("boton","consultar")
        val intent = Intent(this,consultar::class.java)
        startActivity(intent)
    }
}