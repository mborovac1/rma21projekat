package ba.etf.rma21.projekat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import ba.etf.rma21.projekat.viewmodel.UpisPredmetViewModel

class UpisPredmet : AppCompatActivity() {
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

    private var upisViewModel = UpisPredmetViewModel()

    private var odabraniPredmet = ""
    private var odabranaGrupa = ""
    private var odabranaGodina = ""

    private var myListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?,
                                    position: Int, id: Long) {
            if (parent != null) { // da ne moram pisati npr parent?.id umjestp parent.id
                if (parent.id == R.id.odabirGodina) {
                    odabranaGodina = spinnerGodina.selectedItem.toString()
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
                                upisViewModel.getNeupisani(odabranaGodina.toInt())
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
                } else if (parent.id == R.id.odabirPredmet) {
                    odabraniPredmet = spinnerPredmet.selectedItem.toString()

                    if (!odabraniPredmet.equals("Odaberite predmet") &&
                            !odabraniPredmet.equals("")) {
                        spinnerGrupa.isEnabled = true
                        listaGrupa.clear()
                        listaGrupa.add("Odaberite grupu")
                        listaGrupa.addAll(upisViewModel.getGroupsByPredmet(odabraniPredmet))
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

                    dodajPredmetDugme.isEnabled = !odabranaGrupa.equals("Odaberite grupu") &&
                            !odabranaGrupa.equals("")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upis_predmet)

        // inicijalizacija elemenata
        spinnerGodina = findViewById(R.id.odabirGodina)

        spinnerPredmet = findViewById(R.id.odabirPredmet)
        spinnerGrupa = findViewById(R.id.odabirGrupa)
        dodajPredmetDugme = findViewById(R.id.dodajPredmetDugme)

        spinnerGodinaAdapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, listaGodina)
        spinnerGodina.adapter = spinnerGodinaAdapter

        spinnerPredmetAdapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, listaPredmeta)
        spinnerPredmet.adapter = spinnerPredmetAdapter

        spinnerGrupaAdapter = ArrayAdapter(this,
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
            finish()
        }
    }
}
