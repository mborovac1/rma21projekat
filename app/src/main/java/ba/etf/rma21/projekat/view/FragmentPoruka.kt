package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Grupa

class FragmentPoruka(grupa: Grupa) : Fragment() {
    private var grupa = grupa
    private lateinit var poruka: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.poruka_fragment, container, false)
        poruka = view.findViewById(R.id.poruka)
        poruka.text = "Uspješno ste upisani u grupu ${grupa.naziv} predmeta ${grupa.nazivPredmeta}!"

        return view
    }
}
