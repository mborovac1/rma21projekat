package ba.etf.rma21.projekat.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Kviz
import java.text.SimpleDateFormat
import java.util.*

class KvizListAdapter(
        private var kvizovi: List<Kviz>
) : RecyclerView.Adapter<KvizListAdapter.KvizViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KvizViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_kviz, parent, false)
        return KvizViewHolder(view)
    }

    override fun onBindViewHolder(holder: KvizViewHolder, position: Int) {
        holder.nazivPredmeta.text = kvizovi[position].nazivPredmeta
        holder.nazivKviza.text = kvizovi[position].naziv
        holder.vrijemeKviza.text = kvizovi[position].trajanje.toString() + " min" // trajanje

        // osvojeni bodovi
        if (kvizovi[position].osvojeniBodovi != null)
            holder.bodoviKviza.text = kvizovi[position].osvojeniBodovi.toString()
        else
            holder.bodoviKviza.text = ""

        // format za datum kviza (npr 20.04.2021)
        val formatter = SimpleDateFormat("dd.MM.yyyy")

        // status i datum kviza
        var bojaMatch = ""
        val danasnjiDatum: Date = Calendar.getInstance().time

        if (kvizovi[position].osvojeniBodovi != null) {
            bojaMatch = "plava"
            val datum = kvizovi[position].datumRada
            holder.datumKviza.text = formatter.format(datum)
        }
        else if (kvizovi[position].datumPocetka.before(danasnjiDatum) &&
                 kvizovi[position].datumKraj.after(danasnjiDatum)) {
            bojaMatch = "zelena"
            val datum = kvizovi[position].datumKraj
            holder.datumKviza.text = formatter.format(datum)
        }
        else if (kvizovi[position].datumPocetka.after(danasnjiDatum)) {
            bojaMatch = "zuta"
            val datum = kvizovi[position].datumPocetka
            holder.datumKviza.text = formatter.format(datum)
        }
        else if (kvizovi[position].datumPocetka.before(danasnjiDatum) &&
                 kvizovi[position].datumKraj.before(danasnjiDatum) &&
                 kvizovi[position].datumRada == null) {
            bojaMatch = "crvena"
            val datum = kvizovi[position].datumKraj
            holder.datumKviza.text = formatter.format(datum)
        }

        val context: Context = holder.statusPredmeta.context
        var id: Int = context.resources.getIdentifier(bojaMatch, "drawable", context.packageName)
        holder.statusPredmeta.setImageResource(id)
    }

    override fun getItemCount(): Int = kvizovi.size

    fun updateKvizovi(kvizovi: List<Kviz>) {
        this.kvizovi = kvizovi.sortedBy { kviz -> kviz.datumPocetka }
        notifyDataSetChanged()
    }

    inner class KvizViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nazivKviza: TextView = itemView.findViewById(R.id.nazivKviza)
        val nazivPredmeta: TextView = itemView.findViewById(R.id.nazivPredmeta)
        val statusPredmeta: ImageView = itemView.findViewById(R.id.statusKviza)
        val datumKviza: TextView = itemView.findViewById(R.id.datumKviza)
        val vrijemeKviza: TextView = itemView.findViewById(R.id.vrijemeKviza)
        val bodoviKviza: TextView = itemView.findViewById(R.id.bodoviKviza)
    }
}
