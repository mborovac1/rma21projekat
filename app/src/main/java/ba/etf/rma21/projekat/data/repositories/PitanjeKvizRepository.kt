package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.models.pitanja
import ba.etf.rma21.projekat.data.models.pitanjaZaKviz

class PitanjeKvizRepository {
    companion object {
        val svaPitanja = pitanja()
        val svaPitanjaZaKviz = pitanjaZaKviz()

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
    }
}
