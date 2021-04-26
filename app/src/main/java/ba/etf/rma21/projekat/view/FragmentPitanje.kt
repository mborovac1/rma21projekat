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

class FragmentPitanje(pitanje: Pitanje) : Fragment() {
    private val pitanje = pitanje

    private val pitanjeKvizViewModel = PitanjeKvizViewModel()

    private lateinit var tekstPitanja: TextView
    private lateinit var odgovoriLista: ListView
    private lateinit var listaAdapter: ArrayAdapter<String>
    private var listaVrijednosti: ArrayList<String> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val activity = activity as MainActivity
        activity.popraviNavigacijskeOpcije(R.id.predajKviz)

        // za bojenje navigation viewa (navigacijaPitanja) u zavisnosti od tačnosti odgovora
        val bundle = this.arguments as Bundle
        val navigacijaPitanjaIndex: Int = bundle.getInt("navigacijaPitanja_id")
        val nazivKviza: String = bundle.getString("naziv_kviza")!!
        val nazivPredmeta: String = bundle.getString("predmet_kviza")!!

        var view = inflater.inflate(R.layout.pitanje_fragment, container, false)

        tekstPitanja = view.findViewById(R.id.tekstPitanja)
        odgovoriLista = view.findViewById(R.id.odgovoriLista)
        listaVrijednosti.addAll(pitanje.opcije)
        listaAdapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1,
                listaVrijednosti)
        odgovoriLista.adapter = listaAdapter

        tekstPitanja.text = pitanje.tekst

        val navigacijaPitanja: NavigationView = activity.findViewById(R.id.navigacijaPitanja)
        val menuItem = navigacijaPitanja.menu[navigacijaPitanjaIndex]

        val spanString = SpannableString(menuItem.title.toString())

        if (pitanjeKvizViewModel.getOdgovorZaPitanje(pitanje.naziv, nazivKviza, nazivPredmeta) != -1
                && !pitanjeKvizViewModel.getOdabraniKviz().zavrsen) {
            odgovoriLista.isEnabled = false
            val odgovor = pitanjeKvizViewModel.getOdgovorZaPitanje(pitanje.naziv, nazivKviza,
                    nazivPredmeta)
            val tacanOdgovorBoja: Int = Color.parseColor("#3DDC84")
            val netacanOdgovorBoja: Int = Color.parseColor("#DB4F3D")
            odgovoriLista.post {
                if (odgovor == pitanje.tacan) {
                    odgovoriLista[odgovor].setBackgroundColor(tacanOdgovorBoja)
                } else {
                    odgovoriLista[odgovor].setBackgroundColor(netacanOdgovorBoja)
                    odgovoriLista[pitanje.tacan].setBackgroundColor(tacanOdgovorBoja)
                }
            }
        } else if (pitanjeKvizViewModel.getOdabraniKviz().zavrsen) {
            val tacanOdgovorBoja: Int = Color.parseColor("#3DDC84")
            val netacanOdgovorBoja: Int = Color.parseColor("#DB4F3D")
            activity.popraviNavigacijskeOpcije(R.id.kvizovi)
            odgovoriLista.isEnabled = false
            val odgovor = pitanjeKvizViewModel.getOdgovorZaPitanje(pitanje.naziv, nazivKviza,
                    nazivPredmeta)
            if (odgovor != -1) {
                odgovoriLista.post {
                    if (odgovor == pitanje.tacan) {
                        odgovoriLista[odgovor].setBackgroundColor(tacanOdgovorBoja)
                    } else {
                        odgovoriLista[odgovor].setBackgroundColor(netacanOdgovorBoja)
                        odgovoriLista[pitanje.tacan].setBackgroundColor(tacanOdgovorBoja)
                    }
                }
            }
        } else {
            odgovoriLista.setOnItemClickListener { parent, view1, position, id ->
                val element = parent.getChildAt(position)
                val boja = element as TextView
                boja.setTextColor(Color.parseColor("#FF000000")) // boja teksta - crna

                pitanjeKvizViewModel.postaviOdgovor(pitanje.naziv, nazivKviza, nazivPredmeta,
                        position)

                val tacanOdgovor: Boolean = pitanjeKvizViewModel.validacijaOdgovora(position,
                        pitanje)
                val tacanOdgovorBoja: Int = Color.parseColor("#3DDC84")
                val netacanOdgovorBoja: Int = Color.parseColor("#DB4F3D")

                if (tacanOdgovor) {
                    element.setBackgroundColor(tacanOdgovorBoja)
                    // bojenje navigation viewa
                    spanString.setSpan(ForegroundColorSpan(tacanOdgovorBoja),
                            0, spanString.length, 0)
                    menuItem.title = spanString
                } else {
                    element.setBackgroundColor(netacanOdgovorBoja)
                    // postavljanje tačnog odgovora na zeleno ako je izabran netačan odgovor
                    parent.getChildAt(pitanje.tacan).setBackgroundColor(tacanOdgovorBoja)
                    // bojenje navigation viewa
                    spanString.setSpan(ForegroundColorSpan(netacanOdgovorBoja),
                            0, spanString.length, 0)
                    menuItem.title = spanString
                }

                odgovoriLista.isEnabled = false
                element.isEnabled = false
            }
        }

        return view
    }
}
