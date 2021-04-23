package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.viewmodel.KvizListViewModel
import ba.etf.rma21.projekat.viewmodel.PitanjeKvizViewModel

class FragmentKvizovi : Fragment() {
    private var kvizListViewModel = KvizListViewModel()
    private var pitanjeKvizViewModel = PitanjeKvizViewModel()

    private lateinit var kvizovi: RecyclerView
    private lateinit var kvizAdapter: KvizListAdapter

    private lateinit var spinner: Spinner
    private val spinnerListaVrijednosti = arrayListOf("Svi moji kvizovi", "Svi kvizovi",
            "Urađeni kvizovi", "Budući kvizovi", "Prošli kvizovi")
    private lateinit var spinnerAdapter: ArrayAdapter<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val activity = activity as MainActivity
        activity.popraviNavigacijskeOpcije(R.id.kvizovi)

        var view = inflater.inflate(R.layout.kvizovi_fragment, container, false)

        kvizovi = view.findViewById(R.id.listaKvizova)
        kvizovi.layoutManager = GridLayoutManager(activity, 2) // 2 kolone
        kvizAdapter = KvizListAdapter(listOf()) { kviz ->
            pitanjeKvizViewModel.setNazivKviza(kviz.naziv)
            pitanjeKvizViewModel.setNazivPredmeta(kviz.nazivPredmeta)
            pitanjeKvizViewModel.resetujBrojTacnihOdgovora()
            showKvizDetails(kviz)
        }
        kvizovi.adapter = kvizAdapter
        //kvizAdapter.updateKvizovi(kvizListViewModel.getMojiKvizovi())

        spinner = view.findViewById(R.id.filterKvizova)
        spinnerAdapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1,
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

        return view
    }

    companion object {
        fun newInstance(): FragmentKvizovi = FragmentKvizovi()
    }

    private fun openFragment(fragment: Fragment) {
        if (fragmentManager != null) {
            val transaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun showKvizDetails(kviz: Kviz) {
        val fragmentPokusaj = FragmentPokusaj(pitanjeKvizViewModel
                .getPitanja(kviz.naziv, kviz.nazivPredmeta))
        openFragment(fragmentPokusaj)
    }
}
