package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Pitanje

class FragmentPitanje(pitanje: Pitanje) : Fragment() {
    private var pitanje = pitanje
    private lateinit var tekstPitanja: TextView
    private lateinit var odgovoriLista: ListView
    private var listaVrijednosti: ArrayList<String> = arrayListOf()
    private lateinit var listaAdapter: ArrayAdapter<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.pitanje_fragment, container, false)
        val activity = activity as MainActivity
        val menu = activity.getBottomNavigation().menu

        menu.findItem(R.id.kvizovi).isVisible = false
        menu.findItem(R.id.predmeti).isVisible = false
        menu.findItem(R.id.predajKviz).isVisible = true
        menu.findItem(R.id.zaustaviKviz).isVisible = true

        tekstPitanja = view.findViewById(R.id.tekstPitanja)
        odgovoriLista = view.findViewById(R.id.odgovoriLista)
        listaVrijednosti.addAll(pitanje.opcije)
        listaAdapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1,
            listaVrijednosti)
        odgovoriLista.adapter = listaAdapter

        tekstPitanja.text = pitanje.tekst

        return view
    }
}
