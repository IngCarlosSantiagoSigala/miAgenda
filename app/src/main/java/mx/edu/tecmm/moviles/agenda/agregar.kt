package mx.edu.tecmm.moviles.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log

class agregar : AppCompatActivity() {

    lateinit var txtNombre: EditText
    lateinit var  txtApellido: EditText
    lateinit var  txtEdad : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar)

        txtNombre = findViewById(R.id.txtNombre)
        txtApellido = findViewById(R.id.txtApellido)
        txtEdad = findViewById(R.id.txtEdad)


    }

   /* fun mostrarDatos(){
        if (intent.hasExtra("modificar")){
            txtNombre.setText(intent.getStringExtra("nombre"))
            txtApellido.setText(intent.getStringExtra("apellido"))
            txtEdad.setText(intent.getStringExtra("edad"))
            val titulo : TextView = findViewById(R.id.lblTitulo)
            titulo.text = "Modificar"
        }
    }

      fun operacion(v: View){
        if (intent.hasExtra("modificar")){
            toGuardar(v)
            Toast.makeText(this ,"modi",Toast.LENGTH_LONG).show()
        }else {
            toAgregar(v)
            Toast.makeText(this ,"agre",Toast.LENGTH_LONG).show()
        }
    }

     fun toGuardar(v: View){
        val persona=Persona(txtNombre.text.toString(),txtApellido.text.toString(),txtEdad.text.toString(),intent.getStringExtra("id")!!.toInt())
        Log.e("BOTON","pesionado")
        Log.e("Datos","Nombre: ${persona.nombre}")
        Log.e("Datos","Apellido: ${persona.apellido}")
        Log.e("Datos","Edad: ${persona.edad}")
        retrofitModificar(persona)
    }

    fun retrofitModificar(persona: Persona){
        val retrofit=RetrofitClase.getRetrofit()
        val retrofitService=retrofit.create(IPersona::class.java)
        val lapeticion: Call<Resultado> = retrofitService.update(persona)

        lapeticion.enqueue(object: Callback<Resultado> {
            override fun onResponse(call: Call<Resultado>, response: Response<Resultado>) {
                val resultado=response.body()
                terminaRetrofit(resultado!!)
            }

            override fun onFailure(call: Call<Resultado>, t: Throwable) {
                Log.e("Modificar","El error es ${t.message}")
                t.printStackTrace()
            }

        })
    }

    */


    fun toAgregar(v:View){
        val persona = Persona(txtNombre.text.toString(),
                                txtApellido.text.toString(),
                                    txtEdad.text.toString(),
                                    0)

        retrofitAgregar(persona)
    }

    fun retrofitAgregar(persona:Persona){
        val retrofit = RetrofitClase.getRetrofit()
        val retrofitService = retrofit.create(IPersona::class.java)
        val peticion: Call<Resultado> = retrofitService.agregar(persona);

        peticion.enqueue(object:Callback<Resultado>{
            override fun onResponse(call: Call<Resultado>, response: Response<Resultado>) {
                val resultado = response.body();
                terminaRetrofit(resultado!!)
            }

            override fun onFailure(call: Call<Resultado>, t: Throwable) {
               Log.e("Agregar", "El error es ${t.message}")
                t.printStackTrace()
            }

        })
    }

    fun terminaRetrofit(resultado: Resultado){
        if (resultado.estado.equals("OK")){
            Toast.makeText(this ,"Se grabo correctamente",Toast.LENGTH_LONG).show()
            finish()
        } else {
            Toast.makeText(this ,"Ocurrio Un error",Toast.LENGTH_LONG).show()
        }
    }
}