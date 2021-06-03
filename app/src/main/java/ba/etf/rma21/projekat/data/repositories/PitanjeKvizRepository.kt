package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Pitanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PitanjeKvizRepository {
    companion object {
        suspend fun getPitanja(idKviza: Int): List<Pitanje>? {
            return withContext(Dispatchers.IO) {
                val response = ApiAdapter.retrofit.getPitanja(idKviza)
                val responseBody = response.body()
                return@withContext responseBody
            }
        }
    }
}
