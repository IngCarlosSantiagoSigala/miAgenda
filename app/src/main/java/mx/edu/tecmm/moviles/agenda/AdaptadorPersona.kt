package mx.edu.tecmm.moviles.agenda

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorPersona(context:Context,
                        private var datos:List<Persona>, private var clickCell: IClickListener ):
                        RecyclerView.Adapter<AdaptadorPersona.ViewHolderPersona>()   {

    //clase interna
    class  ViewHolderPersona(item:View):
            RecyclerView.ViewHolder (item){

                var txtNombre : TextView = item.findViewById(R.id.txtNombre)
                var txtApellido : TextView = item.findViewById(R.id.txtApellido)
                var txtEdad : TextView = item.findViewById(R.id.txtEdad)
                var txtId: TextView = item.findViewById(R.id.txtid)
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPersona {
        val layoutItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_layout,parent,false)
        return ViewHolderPersona(layoutItem)
    }

    override fun onBindViewHolder(holder: ViewHolderPersona, position: Int) {
       val persona = datos[position]
        holder.txtNombre.text = persona.nombre
        holder.txtApellido.text = persona.apellido
        holder.txtEdad.text = persona.edad
        holder.txtId.text = persona.id.toString()
        holder.itemView.setOnClickListener{
            clickCell.onCellClick(persona)
        }
    }

    override fun getItemCount(): Int = datos.size

}