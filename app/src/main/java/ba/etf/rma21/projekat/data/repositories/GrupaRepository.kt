package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.grupe

class GrupaRepository {
    companion object {
        val sveGrupe = grupe()

        fun getGroupsByPredmet(nazivPredmeta: String): List<Grupa> {
            return sveGrupe.filter { grupa -> grupa.nazivPredmeta.equals(nazivPredmeta) }
        }
    }
}
