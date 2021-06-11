package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.Pitanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PitanjeKvizRepository {
    companion object {
        var odabraniKviz: Kviz? = null

        suspend fun getPitanja(idKviza: Int): List<Pitanje>? {
            return withContext(Dispatchers.IO) {
                val response = ApiAdapter.retrofit.getPitanja(idKviza)
                val responseBody = response.body()
                return@withContext responseBody
            }
        }

        /*
        fun postaviOdgovor(nazivPitanja: String, nazivKviza: String, nazivPredmeta: String,
                           pozicija: Int) {
            val sve = getAll()

            for (pitanjeKviz in sve) {
                if (pitanjeKviz.naziv.equals(nazivPitanja) &&
                    pitanjeKviz.predmet.equals(nazivPredmeta) &&
                    pitanjeKviz.kviz.equals(nazivKviza)) {
                    pitanjeKviz.odabraniOdgovor = pozicija
                }
            }
        }
         */

        /*
        fun getOdgovorZaPitanje(nazivPitanja: String, nazivKviza: String, nazivPredmeta: String): Int {
            val sve = getAll()

            for (pitanjeKviz in sve) {
                if (pitanjeKviz.naziv.equals(nazivPitanja) &&
                    pitanjeKviz.predmet.equals(nazivPredmeta) &&
                    pitanjeKviz.kviz.equals(nazivKviza)) {
                    return pitanjeKviz.odabraniOdgovor
                }
            }
            return -2 // nece se nikad desiti
        }
         */

        /*
        fun getRezultatKviza(nazivKviza: String, nazivPredmeta: String): Double {
            val sve = getPitanja(nazivKviza, nazivPredmeta)
            val svaPitanja = getAll()
            var brojTacnih = 0

            for (pitanje in sve) {
                for (pitanjeKviz in svaPitanja) {
                    if (pitanjeKviz.naziv.equals(pitanje.naziv)) {
                        if (pitanjeKviz.odabraniOdgovor == pitanje.tacan)
                            brojTacnih++
                    }
                }
            }

            if (brojTacnih == 0 || sve.size == 0)
                return 0.0

            return String.format("%.1f", (brojTacnih.toDouble() / sve.size) * 100).toDouble()
        }
         */

    }
}

// spirala 4
/*
package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import ba.etf.rma21.projekat.data.models.Pitanje


class PitanjeKvizRepository {
    companion object {
        private lateinit var context:Context
        fun setContext(_context: Context){
            context=_context
        }
        suspend fun getPitanja(idKviza: Int): List<Pitanje>? {
            return emptyList()
        }
    }

}
 */