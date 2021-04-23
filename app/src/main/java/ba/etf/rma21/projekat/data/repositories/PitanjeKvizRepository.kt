package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.models.pitanja
import ba.etf.rma21.projekat.data.models.pitanjaZaKviz

class PitanjeKvizRepository {
    companion object {
        private val svaPitanja = pitanja()
        private val svaPitanjaZaKviz = pitanjaZaKviz()
        private var brojTacnihOdgovora: Int = 0
        private var nazivKviza: String = ""
        private var nazivPredmeta: String = ""

        fun getPitanja(nazivKviza: String, nazivPredmeta: String): List<Pitanje> {
            val rezultat: ArrayList<Pitanje> = arrayListOf()

            for (pitanjaZaKviz in svaPitanjaZaKviz) {
                if (pitanjaZaKviz.kviz.equals(nazivKviza) &&
                        pitanjaZaKviz.predmet.naziv.equals(nazivPredmeta)) {
                    for (pitanje in svaPitanja) {
                        if (pitanjaZaKviz.naziv.equals(pitanje.naziv)) {
                            rezultat.add(pitanje)
                        }
                    }
                }
            }

            return rezultat
        }

        fun povecajBrojTacnihOdgovora() {
            brojTacnihOdgovora++
        }

        fun resetujBrojTacnihOdgovora() {
            brojTacnihOdgovora = 0
        }

        fun getNazivKviza(): String = nazivKviza

        fun setNazivKviza(naziv: String) {
            nazivKviza = naziv
        }

        fun getNazivPredmeta(): String = nazivPredmeta

        fun setNazivPredmeta(naziv: String) {
            nazivPredmeta = naziv
        }

        fun getRezultatKviza(): Double {
            if (getPitanja(nazivKviza, nazivPredmeta).isEmpty() || brojTacnihOdgovora == 0)
                return 0.toDouble()

            return String.format("%.1f",
                    (brojTacnihOdgovora.toDouble() / getPitanja(nazivKviza, nazivPredmeta).size)
                            * 100).toDouble()
        }
    }
}
