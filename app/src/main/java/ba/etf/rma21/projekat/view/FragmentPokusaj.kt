package ba.etf.rma21.projekat.view

import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.viewmodel.PitanjeKvizViewModel
import com.google.android.material.navigation.NavigationView

class FragmentPokusaj(listaPitanja: List<Pitanje>) : Fragment() {
    private val pitanjeKvizViewModel = PitanjeKvizViewModel()

    private var listaPitanja: List<Pitanje> = listaPitanja
    private lateinit var navigacijaPitanja: NavigationView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var bundle = this.arguments as Bundle
        var nazivKviza = bundle.getString("naziv_kviza")
        var nazivPredmeta = bundle.getString("predmet_kviza")

        (activity as MainActivity).popraviNavigacijskeOpcije(R.id.predajKviz)

        var view = inflater.inflate(R.layout.pokusaj_fragment, container, false)

        navigacijaPitanja = view.findViewById(R.id.navigacijaPitanja)

        for (i in 0 until listaPitanja.size)
            navigacijaPitanja.menu.add(R.id.grupa, i, i, (i + 1).toString())


        if (pitanjeKvizViewModel.getOdabraniKviz().zavrsen) {
            (activity as MainActivity).popraviNavigacijskeOpcije(R.id.kvizovi)

            for (i in 0 until listaPitanja.size) {
                val menuItem = navigacijaPitanja.menu[i]
                val spanString = SpannableString(menuItem.title.toString())

                pitanjeKvizViewModel.getOdgovorZaPitanje(listaPitanja[i].naziv,
                        pitanjeKvizViewModel.getOdabraniKviz().naziv,
                        pitanjeKvizViewModel.getOdabraniKviz().nazivPredmeta)

                if (pitanjeKvizViewModel.getOdgovorZaPitanje(listaPitanja[i].naziv,
                                pitanjeKvizViewModel.getOdabraniKviz().naziv,
                                pitanjeKvizViewModel.getOdabraniKviz().nazivPredmeta) ==
                        listaPitanja[i].tacan) {
                    spanString.setSpan(ForegroundColorSpan(Color.parseColor("#3DDC84")),
                            0, spanString.length, 0)
                } else {
                    spanString.setSpan(ForegroundColorSpan(Color.parseColor("#DB4F3D")),
                            0, spanString.length, 0)
                }
                menuItem.title = spanString
            }

            navigacijaPitanja.menu.add(R.id.grupa, listaPitanja.size, listaPitanja.size, "Rezultat")

        } else if (pitanjeKvizViewModel.getOdabraniKviz().prekinut) {
            for (i in 0 until listaPitanja.size) {
                val menuItem = navigacijaPitanja.menu[i]
                val spanString = SpannableString(menuItem.title.toString())

                if (pitanjeKvizViewModel.getOdgovorZaPitanje(listaPitanja[i].naziv,
                                pitanjeKvizViewModel.getOdabraniKviz().naziv,
                                pitanjeKvizViewModel.getOdabraniKviz().nazivPredmeta) == -1) {
                    continue
                } else if (pitanjeKvizViewModel.getOdgovorZaPitanje(listaPitanja[i].naziv,
                                pitanjeKvizViewModel.getOdabraniKviz().naziv,
                                pitanjeKvizViewModel.getOdabraniKviz().nazivPredmeta)
                        == listaPitanja[i].tacan) {
                    spanString.setSpan(ForegroundColorSpan(Color.parseColor("#3DDC84")),
                            0, spanString.length, 0)
                } else {
                    spanString.setSpan(ForegroundColorSpan(Color.parseColor("#DB4F3D")),
                            0, spanString.length, 0)
                }
                menuItem.title = spanString
            }
        }

        navigacijaPitanja.setNavigationItemSelectedListener {
            val bundleNovi = Bundle()
            bundleNovi.putInt("navigacijaPitanja_id", it.itemId)
            bundleNovi.putString("naziv_kviza", nazivKviza)
            bundleNovi.putString("predmet_kviza", nazivPredmeta)
            bundleNovi.putInt("rezultat_id", listaPitanja.size)

            if (it.itemId == listaPitanja.size) {
                val rezultatKviza = pitanjeKvizViewModel
                        .getRezultatKviza(pitanjeKvizViewModel.getOdabraniKviz().naziv,
                                pitanjeKvizViewModel.getOdabraniKviz().nazivPredmeta)
                val poruka = "Završili ste kviz " +
                        pitanjeKvizViewModel.getOdabraniKviz().naziv +
                        " sa tačnosti " + rezultatKviza
                val fragmentPoruka = FragmentPoruka(poruka)
                val transaction = (activity as MainActivity).supportFragmentManager
                        .beginTransaction()
                transaction.replace(R.id.container, fragmentPoruka)
                transaction.addToBackStack(null)
                transaction.commit()
            } else {
                val fragmentPitanje = FragmentPitanje(listaPitanja[it.itemId])
                fragmentPitanje.arguments = bundleNovi
                openFragment(fragmentPitanje)

            }
            //it.isEnabled = false
            true
        }
        return view
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction()
        transaction.replace(R.id.framePitanje, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
