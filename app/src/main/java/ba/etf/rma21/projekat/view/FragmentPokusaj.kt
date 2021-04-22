package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu.NONE
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Pitanje
import com.google.android.material.navigation.NavigationView

class FragmentPokusaj(listaPitanja: List<Pitanje>) : Fragment() {
    private var listaPitanja: List<Pitanje> = listaPitanja
    private lateinit var navigacijaPitanja: NavigationView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val activity = activity as MainActivity
        val menu = activity.getBottomNavigation().menu

        activity.getBottomNavigation().selectedItemId = R.id.predajKviz

        menu.findItem(R.id.kvizovi).isVisible = false
        menu.findItem(R.id.predmeti).isVisible = false
        menu.findItem(R.id.predajKviz).isVisible = true
        menu.findItem(R.id.zaustaviKviz).isVisible = true

        var view = inflater.inflate(R.layout.pokusaj_fragment, container, false)

        navigacijaPitanja = view.findViewById(R.id.navigacijaPitanja)

        for (i in 0 until listaPitanja.size)
            navigacijaPitanja.menu.add(NONE, i, NONE, (i + 1).toString())

        navigacijaPitanja.setNavigationItemSelectedListener {
            openFragment(FragmentPitanje(listaPitanja[it.itemId]))
            true
        }

        return view
    }

    private fun openFragment(fragment: Fragment) {
        if (fragmentManager != null) {
            val transaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framePitanje, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}
