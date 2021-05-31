package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Pitanje

class PitanjeKvizRepository {
    companion object {
        suspend fun getPitanja(idKviza: Int): List<Pitanje>? {
            return emptyList()
        }
    }
}
