package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.*
import java.util.*
import kotlin.collections.ArrayList

class PitanjeKvizRepository {
    companion object {
        private val svaPitanja = pitanja()
        private val svaPitanjaZaKviz = pitanjaZaKviz()
        var odabraniKviz: Kviz? = null

        fun getAll(): List<PitanjeKviz> = svaPitanjaZaKviz

        fun getAllPitanja(): List<Pitanje> = svaPitanja

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
    }
}
