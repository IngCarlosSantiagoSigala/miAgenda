package mx.edu.tecmm.moviles.agenda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class consultar : AppCompatActivity(), IClickListener {

    lateinit var recycler: RecyclerView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar)

        recycler = findViewById(R.id.contactos_rv)
        obtenerDatos()
    // crear mi abdatador y mandar llamar los los datos


    }

    fun acomodarRecycler(ListaPersona: List<Persona>){
        val adaptador = AdaptadorPersona(this, ListaPersona, this)
        recycler.adapter = adaptador
    }

    fun obtenerDatos(){

        val retrofit = RetrofitClase.getRetrofit()
        val retrofitService = retrofit.create(IPersona::class.java)
        val peticion: Call<List<Persona>> = retrofitService.getListaPersona()
        peticion.enqueue(object : Callback<List<Persona>> {
            override fun onResponse(call: Call<List<Persona>>, response: Response<List<Persona>>) {
                val listaPersona = response.body()
                acomodarRecycler(listaPersona!!);
            }

            override fun onFailure(call: Call<List<Persona>>, t: Throwable) {
                Log.e("Error", "Paso algo")
                t.printStackTrace()
            }

        })
    }

    override fun onCellClick(persona: Persona) {
        Log.e("persona", "${persona.id}")
        val intent = Intent(this, modificar::class.java)
        intent.putExtra("nombre", persona.nombre)
        intent.putExtra("edad", persona.edad)
        intent.putExtra("apellido", persona.apellido)
        var id : String = persona.id.toString()
        intent.putExtra("id", id)
        //intent.putExtra("modificar", "yes")
        startActivity(intent)
    }
}