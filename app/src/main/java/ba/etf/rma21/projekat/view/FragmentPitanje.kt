package ba.etf.rma21.projekat.view

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.viewmodel.PitanjeKvizViewModel
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentPitanje(pitanje: Pitanje) : Fragment() {
    private val pitanje = pitanje

    private val pitanjeKvizViewModel = PitanjeKvizViewModel()

    private lateinit var tekstPitanja: TextView
    private lateinit var odgovoriLista: ListView
    private lateinit var listaAdapter: ArrayAdapter<String>
    private var listaVrijednosti: ArrayList<String> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = activity as MainActivity
        activity.popraviNavigacijskeOpcije(R.id.predajKviz)

        val bundle = this.arguments as Bundle
        val navigacijaPitanjaIndex: Int = bundle.getInt("navigacijaPitanja_id")
        val idKviza: Int = bundle.getInt("id_kviza")
        val idKvizTaken = bundle.getInt("id_kt")

        var view = inflater.inflate(R.layout.pitanje_fragment, container, false)

        tekstPitanja = view.findViewById(R.id.tekstPitanja)
        odgovoriLista = view.findViewById(R.id.odgovoriLista)
        listaVrijednosti.addAll(pitanje.opcije)
        listaAdapter = ArrayAdapter(
            view.context, android.R.layout.simple_list_item_1,
            listaVrijednosti
        )
        odgovoriLista.adapter = listaAdapter

        tekstPitanja.text = pitanje.tekstPitanja

        val navigacijaPitanja: NavigationView = activity.findViewById(R.id.navigacijaPitanja)
        val menuItem = navigacijaPitanja.menu[navigacijaPitanjaIndex]

        val spanString = SpannableString(menuItem.title.toString())

        odgovoriLista.setOnItemClickListener { parent, view1, position, id ->
            GlobalScope.launch(Dispatchers.IO) {
                val element = parent.getChildAt(position)
                val boja = element as TextView
                boja.setTextColor(Color.parseColor("#FF000000")) // boja teksta - crna

                val kvizTaken = pitanjeKvizViewModel.getKvizTakenById(idKvizTaken)

                pitanjeKvizViewModel.postaviOdgovorKviz(kvizTaken!!.id, pitanje.id, position)

                val tacanOdgovor: Boolean = pitanjeKvizViewModel.validacijaOdgovora(
                    position,
                    pitanje
                )
                val tacanOdgovorBoja: Int = Color.parseColor("#3DDC84")
                val netacanOdgovorBoja: Int = Color.parseColor("#DB4F3D")

                if (tacanOdgovor) {
                    withContext(Dispatchers.Main) {
                        element.setBackgroundColor(tacanOdgovorBoja)
                        // bojenje navigation viewa
                        spanString.setSpan(
                            ForegroundColorSpan(tacanOdgovorBoja),
                            0, spanString.length, 0
                        )
                        menuItem.title = spanString
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        element.setBackgroundColor(netacanOdgovorBoja)
                        // postavljanje tačnog odgovora na zeleno ako je izabran netačan odgovor
                        parent.getChildAt(pitanje.tacan).setBackgroundColor(tacanOdgovorBoja)
                        // bojenje navigation viewa
                        spanString.setSpan(
                            ForegroundColorSpan(netacanOdgovorBoja),
                            0, spanString.length, 0
                        )
                        menuItem.title = spanString
                    }
                }
            }
        }
        return view
    }
}
