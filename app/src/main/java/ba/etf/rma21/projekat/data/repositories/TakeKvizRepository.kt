package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.KvizTaken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TakeKvizRepository {
    companion object {
        var odabraniKvizTaken: KvizTaken? = null

        suspend fun zapocniKviz(kid: Int): KvizTaken? {
            return withContext(Dispatchers.IO) {
                val response = ApiAdapter.retrofit.zapocniKviz(AccountRepository.acHash, kid)
                val responseBody = response.body()
                return@withContext responseBody
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

        suspend fun getKvizTakenById(id: Int): KvizTaken? {
            return withContext(Dispatchers.IO) {
                val pocetiKvizovi = getPocetiKvizovi()

                for (kvizTaken in pocetiKvizovi!!) {
                    if (kvizTaken.id == id) {
                        return@withContext kvizTaken
                    }
                }

                return@withContext null
            }
        }

        suspend fun getRezultatKviza(id: Int): Int {
            return withContext(Dispatchers.IO) {
                val kvizTaken = getKvizTakenById(id)

                if (kvizTaken != null) {
                    return@withContext kvizTaken.osvojeniBodovi!!
                }

                return@withContext 0
            }
        }

        suspend fun getMojOdgovorNaPitanje(idKviza: Int, idPitanja: Int): Int {
            return withContext(Dispatchers.IO) {
                val kvizTaken = getKvizTakenById(idKviza)
                val odgovori = OdgovorRepository.getOdgovoriKviz(idKviza)

                for (odgovor in odgovori!!) {
                    if (odgovor.KvizTakenId == kvizTaken!!.id && odgovor.PitanjeId == idPitanja) {
                        return@withContext odgovor.odgovoreno
                    }
                }

                return@withContext -1
            }
        }

    }
}
