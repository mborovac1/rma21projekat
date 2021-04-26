package ba.etf.rma21.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import ba.etf.rma21.projekat.MainActivity
import ba.etf.rma21.projekat.R
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository
import ba.etf.rma21.projekat.viewmodel.UpisPredmetViewModel

class FragmentPredmeti : Fragment() {
    private val upisViewModel = UpisPredmetViewModel()

    private lateinit var spinnerGodina: Spinner
    private lateinit var spinnerGodinaAdapter: ArrayAdapter<String>
    private val listaGodina = arrayListOf("Odaberite godinu", "1", "2", "3", "4", "5")

    private lateinit var spinnerPredmet: Spinner
    private lateinit var spinnerPredmetAdapter: ArrayAdapter<String>
    private val listaPredmeta = arrayListOf<String>()

    private lateinit var spinnerGrupa: Spinner
    private lateinit var spinnerGrupaAdapter: ArrayAdapter<String>
    private val listaGrupa: ArrayList<String> = arrayListOf()

    private lateinit var dodajPredmetDugme: Button

    private var odabraniPredmet = ""
    private var odabranaGrupa = ""
    private var odabranaGodina = ""

    private var myListener = object : AdapterView.OnItemSelectedListener {

        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?,
                                    position: Int, id: Long) {
            var bundle = Bundle()
            var fragmentKvizovi = FragmentKvizovi.newInstance()

            if (parent != null) { // da ne moram pisati npr parent?.id umjestp parent.id
                if (parent.id == R.id.odabirGodina) {
                    if (KorisnikRepository.getGodinaStudija() != 0) {
                        spinnerGodina.isEnabled = true
                        spinnerGodina.setSelection(KorisnikRepository.getGodinaStudija())
                        KorisnikRepository.setGodinaStudija(0)
                    } else {
                        odabranaGodina = spinnerGodina.selectedItem.toString()
                        bundle.putString("odabrana_godina", odabranaGodina)
                        if (!odabranaGodina.equals("Odaberite godinu")) {
                            dodajPredmetDugme.isEnabled = false
                            spinnerPredmet.isEnabled = true
                            listaGrupa.clear()
                            listaGrupa.add("")
                            spinnerGrupa.setSelection(0)
                            spinnerGrupa.isEnabled = false
                            spinnerGrupaAdapter.notifyDataSetChanged()

                            listaPredmeta.clear()
                            listaPredmeta.add("Odaberite predmet")
                            listaPredmeta.addAll(
                                    upisViewModel.getNeupisaniNazivi(odabranaGodina.toInt())
                            )
                            spinnerPredmet.setSelection(0)
                            spinnerPredmetAdapter.notifyDataSetChanged()
                        } else if (spinnerPredmet.isEnabled) {
                            listaPredmeta.clear()
                            listaPredmeta.add("")
                            spinnerPredmet.setSelection(0)
                            spinnerPredmet.isEnabled = false
                            spinnerPredmetAdapter.notifyDataSetChanged()

                            listaGrupa.clear()
                            listaGrupa.add("")
                            spinnerGrupa.setSelection(0)
                            spinnerGrupa.isEnabled = false
                            spinnerGrupaAdapter.notifyDataSetChanged()
                        }
                    }
                } else if (parent.id == R.id.odabirPredmet) {
                    odabraniPredmet = spinnerPredmet.selectedItem.toString()
                    bundle.putString("odabrani_predmet", odabraniPredmet)

                    if (!odabraniPredmet.equals("Odaberite predmet") &&
                            !odabraniPredmet.equals("")) {
                        spinnerGrupa.isEnabled = true
                        listaGrupa.clear()
                        listaGrupa.add("Odaberite grupu")
                        listaGrupa.addAll(upisViewModel.getNaziviGroupaZaPredmet(odabraniPredmet))
                        spinnerGrupa.setSelection(0)
                        spinnerGrupaAdapter.notifyDataSetChanged()
                    } else {
                        listaGrupa.clear()
                        listaGrupa.add("")
                        spinnerGrupa.setSelection(0)
                        spinnerGrupa.isEnabled = false
                        spinnerGrupaAdapter.notifyDataSetChanged()
                    }
                } else if (parent.id == R.id.odabirGrupa) {
                    odabranaGrupa = spinnerGrupa.selectedItem.toString()
                    bundle.putString("odabrana_grupa", odabranaGrupa)

                    dodajPredmetDugme.isEnabled = !odabranaGrupa.equals("Odaberite grupu") &&
                            !odabranaGrupa.equals("")
                }
            }

            fragmentKvizovi.arguments = bundle
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val activity = activity as MainActivity
        activity.popraviNavigacijskeOpcije(R.id.predmeti)

        var view = inflater.inflate(R.layout.predmeti_fragment, container, false)

        spinnerGodina = view.findViewById(R.id.odabirGodina)

        spinnerPredmet = view.findViewById(R.id.odabirPredmet)
        spinnerGrupa = view.findViewById(R.id.odabirGrupa)
        dodajPredmetDugme = view.findViewById(R.id.dodajPredmetDugme)

        spinnerGodinaAdapter = ArrayAdapter(view.context,
                android.R.layout.simple_list_item_1, listaGodina)
        spinnerGodina.adapter = spinnerGodinaAdapter

        spinnerPredmetAdapter = ArrayAdapter(view.context,
                android.R.layout.simple_list_item_1, listaPredmeta)
        spinnerPredmet.adapter = spinnerPredmetAdapter

        spinnerGrupaAdapter = ArrayAdapter(view.context,
                android.R.layout.simple_list_item_1, listaGrupa)
        spinnerGrupa.adapter = spinnerGrupaAdapter

        // isti listener za sva tri spinnera
        spinnerGodina.onItemSelectedListener = myListener
        spinnerPredmet.onItemSelectedListener = myListener
        spinnerGrupa.onItemSelectedListener = myListener

        spinnerPredmet.isEnabled = false
        spinnerGrupa.isEnabled = false
        dodajPredmetDugme.isEnabled = false

        dodajPredmetDugme.setOnClickListener {
            upisViewModel.upisiPredmet(odabraniPredmet, odabranaGrupa, odabranaGodina)

            val bundle = Bundle()
            bundle.putString("odabrana_godina", odabranaGodina)

            val poruka = "Uspješno ste upisani u grupu " + odabranaGrupa +
                    " predmeta " + odabraniPredmet + "!"

            val fragmentPoruka = FragmentPoruka.newInstance(poruka)
            fragmentPoruka.arguments = bundle
            openFragment(fragmentPoruka)
        }

        return view
    }

    companion object {
        fun newInstance(): FragmentPredmeti = FragmentPredmeti()
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = (activity as MainActivity).supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
