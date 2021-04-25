package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.repositories.PitanjeKvizRepository

class PitanjeKvizViewModel {
    fun getPitanja(nazivKviza: String, nazivPredmeta: String): List<Pitanje> {
        return PitanjeKvizRepository.getPitanja(nazivKviza, nazivPredmeta)
    }

    fun validacijaOdgovora(pozicija: Int, pitanje: Pitanje): Boolean = pozicija == pitanje.tacan

    fun postaviOdgovor(nazivPitanja: String, nazivKviza: String, nazivPredmeta: String,
                       pozicija: Int) {
        val sve = PitanjeKvizRepository.getAll()

        for (pitanjeKviz in sve) {
            if (pitanjeKviz.naziv.equals(nazivPitanja) &&
                    pitanjeKviz.predmet.equals(nazivPredmeta) &&
                    pitanjeKviz.kviz.equals(nazivKviza)) {
                        pitanjeKviz.odabraniOdgovor = pozicija
            }
        }
    }

    fun getOdgovorZaPitanje(nazivPitanja: String, nazivKviza: String, nazivPredmeta: String): Int {
        val sve = PitanjeKvizRepository.getAll()

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
        val sve = PitanjeKvizRepository.getPitanja(nazivKviza, nazivPredmeta)
        val svaPitanja = PitanjeKvizRepository.getAll()
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

    fun getOdabraniKviz(): Kviz = PitanjeKvizRepository.odabraniKviz!!

    fun setOdabraniKviz(kviz: Kviz) {
        PitanjeKvizRepository.odabraniKviz = kviz
    }
}
