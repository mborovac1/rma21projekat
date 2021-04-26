package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.*

class PitanjeKvizRepository {
    companion object {
        private val svaPitanja = pitanja()
        private val svaPitanjaZaKviz = pitanjaZaKviz()
        var odabraniKviz: Kviz? = null

        fun getAll(): List<PitanjeKviz> = svaPitanjaZaKviz

        fun getPitanja(nazivKviza: String, nazivPredmeta: String): List<Pitanje> {
            val rezultat: ArrayList<Pitanje> = arrayListOf()

            for (pitanjaZaKviz in svaPitanjaZaKviz) {
                if (pitanjaZaKviz.kviz.equals(nazivKviza) &&
                        pitanjaZaKviz.predmet.equals(nazivPredmeta)) {
                    for (pitanje in svaPitanja) {
                        if (pitanjaZaKviz.naziv.equals(pitanje.naziv)) {
                            rezultat.add(pitanje)
                        }
                    }
                }
            }

            return rezultat
        }

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
    }
}
