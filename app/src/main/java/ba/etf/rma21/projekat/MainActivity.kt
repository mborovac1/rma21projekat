package ba.etf.rma21.projekat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.view.FragmentKvizovi
import ba.etf.rma21.projekat.view.FragmentPoruka
import ba.etf.rma21.projekat.view.FragmentPredmeti
import ba.etf.rma21.projekat.viewmodel.PitanjeKvizViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    //private val kvizListViewModel = KvizListViewModel()
    private var pitanjeKvizViewModel = PitanjeKvizViewModel()

    private lateinit var bottomNavigation: BottomNavigationView

    private val mOnNavigationItemSelectedListener =
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.kvizovi -> {
                        val fragmentKvizovi = FragmentKvizovi.newInstance()
                        openFragment(fragmentKvizovi)
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.predmeti -> {
                        val fragmentPredmeti = FragmentPredmeti.newInstance()
                        openFragment(fragmentPredmeti)
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.predajKviz -> {
                        val rezultat = pitanjeKvizViewModel.getRezultatKviza()
                        val poruka = "Završili ste kviz " + pitanjeKvizViewModel.getNazivKviza() +
                                " sa tačnosti " + rezultat.toString()
                        openFragment(FragmentPoruka(poruka))
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.zaustaviKviz -> {
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigation = findViewById(R.id.bottomNav)
        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        bottomNavigation.selectedItemId = R.id.kvizovi
        val fragmentKvizovi = FragmentKvizovi.newInstance()
        openFragment(fragmentKvizovi)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        if (bottomNavigation.selectedItemId != R.id.kvizovi) {
            openFragment(FragmentKvizovi.newInstance())
            bottomNavigation.selectedItemId = R.id.kvizovi
        }
        /*
        else {
            //super.onBackPressed() // bez ovog ne izlazi se iz app klikom na back
        }
         */
    }

    fun getBottomNavigation(): BottomNavigationView = bottomNavigation

    fun popraviNavigacijskeOpcije(opcija: Int) {
        val activity = this
        val menu = activity.getBottomNavigation().menu

        when (opcija) {
            R.id.kvizovi, R.id.predmeti -> {
                menu.findItem(R.id.kvizovi).isVisible = true
                menu.findItem(R.id.predmeti).isVisible = true
                menu.findItem(R.id.predajKviz).isVisible = false
                menu.findItem(R.id.zaustaviKviz).isVisible = false
            }
            R.id.predajKviz, R.id.zaustaviKviz -> {
                menu.findItem(R.id.kvizovi).isVisible = false
                menu.findItem(R.id.predmeti).isVisible = false
                menu.findItem(R.id.predajKviz).isVisible = true
                menu.findItem(R.id.zaustaviKviz).isVisible = true
            }
        }
    }
}
