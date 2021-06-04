package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.viewmodel.KvizListViewModel
import ba.etf.rma21.projekat.viewmodel.PitanjeKvizViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FragmentKvizovi : Fragment() {
    private val kvizListViewModel = KvizListViewModel()
    private val pitanjeKvizViewModel = PitanjeKvizViewModel()

    private lateinit var kvizovi: RecyclerView
    private lateinit var kvizAdapter: KvizListAdapter

    private lateinit var spinner: Spinner
    private val spinnerListaVrijednosti = arrayListOf(
        "Svi moji kvizovi", "Svi kvizovi",
        "Urađeni kvizovi", "Budući kvizovi", "Prošli kvizovi"
    )
    private lateinit var spinnerAdapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val activity = activity as MainActivity
        activity.popraviNavigacijskeOpcije(R.id.kvizovi)

        var view = inflater.inflate(R.layout.kvizovi_fragment, container, false)

        kvizovi = view.findViewById(R.id.listaKvizova)
        kvizovi.layoutManager = GridLayoutManager(activity, 2) // 2 kolone
        kvizAdapter = KvizListAdapter(listOf()) { kviz ->
            pitanjeKvizViewModel.setOdabraniKviz(kviz)
            showKvizDetails(kviz)
        }
        kvizovi.adapter = kvizAdapter

        spinner = view.findViewById(R.id.filterKvizova)
        spinnerAdapter = ArrayAdapter(
            view.context, android.R.layout.simple_list_item_1,
            spinnerListaVrijednosti
        )
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?,
                position: Int, id: Long
            ) {
                when (position) {
                    1 -> kvizListViewModel.getAll(::onSuccess, ::onError)
                    2 -> kvizListViewModel.getDone(::onSuccess, ::onError)
                    3 -> kvizListViewModel.getFuture(::onSuccess, ::onError)
                    4 -> kvizListViewModel.getNotTaken(::onSuccess, ::onError)
                    else -> kvizListViewModel.getMojiKvizovi(::onSuccess, ::onError)
                }
            }
        }

        return view
    }

    companion object {
        fun newInstance(): FragmentKvizovi = FragmentKvizovi()
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun showKvizDetails(kviz: Kviz) {
        GlobalScope.launch(Dispatchers.Main) {
            if (kvizListViewModel.getMyKvizovi().contains(kviz)) {
                val fragmentPokusaj = FragmentPokusaj.newInstance(
                    pitanjeKvizViewModel.getPitanja(kviz.id)
                )

                val kvizTaken = pitanjeKvizViewModel.zapocniKviz(kviz.id)
                pitanjeKvizViewModel.setOdabraniKvizTaken(kvizTaken!!)

                val bundle = Bundle()
                bundle.putInt("id_kviza", kviz.id)
                bundle.putInt("id_kt", kvizTaken!!.id)
                //bundle.putString("naziv_kviza", kviz.naziv)
                bundle.putString("predmet_kviza", kviz.nazivPredmeta)
                fragmentPokusaj.arguments = bundle

                openFragment(fragmentPokusaj)
            }
        }
    }

    fun onSuccess(kvizovi: List<Kviz>) {
        kvizAdapter.updateKvizovi(kvizovi)
    }

    fun onError() {
        val toast = Toast.makeText(context, "Greska", Toast.LENGTH_SHORT)
        toast.show()
    }
}
