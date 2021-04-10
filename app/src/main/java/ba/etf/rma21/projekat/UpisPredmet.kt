package ba.etf.rma21.projekat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class UpisPredmet : AppCompatActivity() {
    private lateinit var godinaSpinner: Spinner
    private lateinit var godinaSpinnerAdapter: ArrayAdapter<String>
    private val godinaSpinnerListaVrijednosti = arrayListOf("1", "2", "3", "4", "5")

    private lateinit var predmetSpinner: Spinner
    private lateinit var predmetSpinnerAdapter: ArrayAdapter<String>
    private val predmetSpinnerListaVrijednosti = arrayListOf("TP", "IM1") // ispraviti

    private lateinit var grupaSpinner: Spinner
    private lateinit var grupaSpinnerAdapter: ArrayAdapter<String>
    private val grupaSpinnerListaVrijednosti = arrayListOf("RI", "AiE") // ispraviti

    private lateinit var dodajPredmetDugme: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upis_predmet)

        // inicijalizacija elemenata
        godinaSpinner = findViewById(R.id.odabirGodina)
        predmetSpinner = findViewById(R.id.odabirPredmet)
        grupaSpinner = findViewById(R.id.odabirGrupa)
        dodajPredmetDugme = findViewById(R.id.dodajPredmetDugme)

        godinaSpinnerAdapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, godinaSpinnerListaVrijednosti)
        godinaSpinner.adapter = godinaSpinnerAdapter

        predmetSpinnerAdapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, predmetSpinnerListaVrijednosti)
        predmetSpinner.adapter = predmetSpinnerAdapter

        grupaSpinnerAdapter = ArrayAdapter(this,
                android.R.layout.simple_list_item_1, grupaSpinnerListaVrijednosti)
        grupaSpinner.adapter = grupaSpinnerAdapter
    }
}
