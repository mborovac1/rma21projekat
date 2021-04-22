package ba.etf.rma21.projekat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.data.repositories.PitanjeKvizRepository
import ba.etf.rma21.projekat.view.FragmentKvizovi
import ba.etf.rma21.projekat.view.FragmentPokusaj
import ba.etf.rma21.projekat.view.FragmentPredmeti
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
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
                    R.id.predajKviz, R.id.zaustaviKviz -> {
                        // dodati nešto (?)
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
}
