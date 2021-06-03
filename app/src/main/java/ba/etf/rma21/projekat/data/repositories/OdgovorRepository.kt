package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Odgovor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class OdgovorRepository {
    companion object {
        suspend fun getOdgovoriKviz(idKviza: Int): List<Odgovor>? {
            return withContext(Dispatchers.IO) {
                val response = ApiAdapter.retrofit.getOdgovoriKviz(
                    AccountRepository.acHash,
                    idKviza
                )
                val responseBody = response.body()
                return@withContext responseBody
            }
        }

        suspend fun postaviOdgovorKviz(idKvizTaken: Int, idPitanje: Int, odgovor: Int): Int? {
            return null
        }
    }
}
