package ba.etf.rma21.projekat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.view.KvizListAdapter
import ba.etf.rma21.projekat.viewmodel.KvizListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var kvizovi: RecyclerView
    private lateinit var kvizAdapter: KvizListAdapter
    private var kvizListViewModel = KvizListViewModel()

    // spinner
    private lateinit var spinner: Spinner
    private val spinnerListaVrijednosti = arrayListOf("Svi moji kvizovi", "Svi kvizovi",
                                  "Urađeni kvizovi", "Budući kvizovi", "Prošli kvizovi")
    private lateinit var spinnerAdapter: ArrayAdapter<String>

    // dugme za upis predmeta (otvara aktivnost "UpisPredmet")
    private lateinit var upisDugme: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        kvizovi = findViewById(R.id.listaKvizova)
        kvizovi.layoutManager = GridLayoutManager(this, 2) // 2 kolone
        kvizAdapter = KvizListAdapter(listOf())
        kvizovi.adapter = kvizAdapter

        // spinner
        spinner = findViewById(R.id.filterKvizova)
        spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,
                                      spinnerListaVrijednosti)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?,
                                        position: Int, id: Long) {
                when (position) {
                    1 -> kvizAdapter.updateKvizovi(kvizListViewModel.getAll())
                    2 -> kvizAdapter.updateKvizovi((kvizListViewModel.getDone()))
                    3 -> kvizAdapter.updateKvizovi(kvizListViewModel.getFuture())
                    4 -> kvizAdapter.updateKvizovi(kvizListViewModel.getNotTaken())
                    else -> kvizAdapter.updateKvizovi(kvizListViewModel.getMojiKvizovi())
                }
            }
        }

        // dugme za upis predmeta
        upisDugme = findViewById(R.id.upisDugme)
        upisDugme.setOnClickListener {
            upisPredmeta()
        }
    }

    private fun upisPredmeta() {
        val intent = Intent(this, UpisPredmet::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        kvizAdapter.updateKvizovi(kvizListViewModel.getMojiKvizovi())
    }
}
