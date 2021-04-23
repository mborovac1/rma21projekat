package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository

class FragmentPoruka(tekst: String) : Fragment() {
    private var tekst = tekst
    private lateinit var poruka: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val activity = activity as MainActivity
        activity.popraviNavigacijskeOpcije(R.id.kvizovi)

        // postavljanje godine nakon upisa
        var bundle = this.arguments
        if (bundle != null) {
            var odabranaGodina: String? = bundle.getString("odabrana_godina")
            if (odabranaGodina != null)
                KorisnikRepository.setGodinaStudija(odabranaGodina.toInt())
        }

        var view = inflater.inflate(R.layout.poruka_fragment, container, false)

        poruka = view.findViewById(R.id.poruka)
        poruka.text = tekst

        return view
    }
}
