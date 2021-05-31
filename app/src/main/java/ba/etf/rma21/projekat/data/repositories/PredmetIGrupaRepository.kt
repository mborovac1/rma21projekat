package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Predmet

class PredmetIGrupaRepository {
    companion object {
        suspend fun getPredmeti(): List<Predmet>? {
            return emptyList()
        }

        suspend fun getGrupe(): List<Grupa>? {
            return emptyList()
        }

        suspend fun getGrupeZaPredmet(idPredmeta: Int): List<Grupa>? {
            return emptyList()
        }

        suspend fun upisiUGrupu(idGrupa: Int): Boolean? {
            return false
        }

        suspend fun getUpisaneGrupe(): List<Grupa>? {
            return emptyList()
        }
    }
}
