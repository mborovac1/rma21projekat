package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.viewmodel.PitanjeKvizViewModel
import com.google.android.material.navigation.NavigationView

class FragmentPokusaj(listaPitanja: List<Pitanje>) : Fragment() {
    private var listaPitanja: List<Pitanje> = listaPitanja

    private val pitanjeKvizViewModel = PitanjeKvizViewModel()

    private lateinit var navigacijaPitanja: NavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).popraviNavigacijskeOpcije(R.id.predajKviz)

        val bundle = this.arguments as Bundle
        val idKviza = bundle.getInt("id_kviza")
        val idKvizTaken = bundle.getInt("id_kt")
        val nazivPredmeta = bundle.getString("predmet_kviza")

        var view = inflater.inflate(R.layout.pokusaj_fragment, container, false)

        navigacijaPitanja = view.findViewById(R.id.navigacijaPitanja)

        for (i in 0 until listaPitanja.size)
            navigacijaPitanja.menu.add(R.id.grupa, i, i, (i + 1).toString())

        navigacijaPitanja.setNavigationItemSelectedListener {
            val bundleNovi = Bundle()
            bundleNovi.putInt("navigacijaPitanja_id", it.itemId)
            bundleNovi.putInt("id_kviza", idKviza)
            bundleNovi.putInt("id_kt", idKvizTaken)
            bundleNovi.putString("predmet_kviza", nazivPredmeta)
            bundleNovi.putInt("rezultat_id", listaPitanja.size)

            val fragmentPitanje = FragmentPitanje(listaPitanja[it.itemId])
            fragmentPitanje.arguments = bundleNovi
            openFragment(fragmentPitanje)
            true
        }

        val fragmentPitanje = FragmentPitanje(listaPitanja[0])
        val bundleNovi = Bundle()
        bundleNovi.putInt("navigacijaPitanja_id", 0)
        bundleNovi.putInt("id_kviza", idKviza)
        bundleNovi.putInt("id_kt", idKvizTaken)
        bundleNovi.putString("predmet_kviza", nazivPredmeta)
        bundleNovi.putInt("rezultat_id", listaPitanja.size)
        fragmentPitanje.arguments = bundleNovi
        openFragment(fragmentPitanje)

        return view
    }


    companion object {
        fun newInstance(listaPitanja: List<Pitanje>): FragmentPokusaj =
            FragmentPokusaj(listaPitanja)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction()
        transaction.replace(R.id.framePitanje, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
