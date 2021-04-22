package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository

class FragmentPoruka(grupa: Grupa) : Fragment() {
    private var grupa = grupa
    private lateinit var poruka: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val activity = activity as MainActivity
        val menu = activity.getBottomNavigation().menu

        menu.findItem(R.id.kvizovi).isVisible = true
        menu.findItem(R.id.predmeti).isVisible = true
        menu.findItem(R.id.predajKviz).isVisible = false
        menu.findItem(R.id.zaustaviKviz).isVisible = false

        // postavljanje godine nakon upisa
        var bundle = this.arguments
        if (bundle != null) {
            var odabranaGodina: String? = bundle.getString("odabrana_godina")
            if (odabranaGodina != null)
                KorisnikRepository.setGodinaStudija(odabranaGodina.toInt())
        }

        var view = inflater.inflate(R.layout.poruka_fragment, container, false)
        poruka = view.findViewById(R.id.poruka)
        poruka.text = "Uspješno ste upisani u grupu ${grupa.naziv} predmeta ${grupa.nazivPredmeta}!"

        return view
    }
}
