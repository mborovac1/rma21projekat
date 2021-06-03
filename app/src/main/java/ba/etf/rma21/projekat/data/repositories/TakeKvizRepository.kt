package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.KvizTaken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TakeKvizRepository {
    companion object {
        suspend fun zapocniKviz(kid: Int) {
            withContext(Dispatchers.IO) {
                ApiAdapter.retrofit.zapocniKviz(AccountRepository.acHash, kid)
            }
        }

        suspend fun getPocetiKvizovi(): List<KvizTaken>? {
            return withContext(Dispatchers.IO) {
                val response = ApiAdapter.retrofit.getPocetiKvizovi(AccountRepository.acHash)
                val responseBody = response.body()

                if (responseBody?.size == 0) {
                    return@withContext null
                }

                return@withContext responseBody
            }
        }
    }
}
