package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Predmet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PredmetIGrupaRepository {
    companion object {
        suspend fun getPredmeti(): List<Predmet>? {
            return withContext(Dispatchers.IO) {
                val response = ApiAdapter.retrofit.getPredmeti()
                val responseBody = response.body()
                return@withContext responseBody
            }
        }

        suspend fun getGrupe(): List<Grupa>? {
            return withContext(Dispatchers.IO) {
                val response = ApiAdapter.retrofit.getGrupe()
                val responseBody = response.body()
                return@withContext responseBody
            }
        }

        suspend fun getGrupeZaPredmet(idPredmeta: Int): List<Grupa>? {
            return withContext(Dispatchers.IO) {
                val response = ApiAdapter.retrofit.getGrupeZaPredmet(idPredmeta)
                val responseBody = response.body()
                return@withContext responseBody
            }
        }

        suspend fun getGrupeZaStudenta(): List<Grupa>? {
            return withContext(Dispatchers.IO) {
                val response = ApiAdapter.retrofit.getGrupeZaStudenta(AccountRepository.acHash)
                val responseBody = response.body()
                return@withContext responseBody
            }
        }

        suspend fun upisiUGrupu(idGrupa: Int): Boolean? {
            return withContext(Dispatchers.IO) {
                val response = ApiAdapter.retrofit.upisiUGrupu(idGrupa, AccountRepository.acHash)
                val responseBody = response.body()
                val poruka: String = responseBody!!.message

                if (poruka.contains("je dodan u grupu")) {
                    return@withContext true
                }

                return@withContext false
            }
        }

        suspend fun getUpisaneGrupe(): List<Grupa>? {
            return withContext(Dispatchers.IO) {
                val response = ApiAdapter.retrofit.getUpisaneGrupe(AccountRepository.acHash)
                val responseBody = response.body()

                if (responseBody == null)
                    return@withContext emptyList()

                return@withContext responseBody
            }
        }

        suspend fun getGrupaById(grupaId: Int): Grupa? {
            return withContext(Dispatchers.IO) {
                val response = ApiAdapter.retrofit.getGrupaById(grupaId)
                val responseBody = response.body()
                return@withContext responseBody
            }
        }

        suspend fun getGrupeZaKviz(kvizId: Int): List<Grupa>? {
            return withContext(Dispatchers.IO) {
                val response = ApiAdapter.retrofit.getGrupeZaKviz(kvizId)
                val responseBody = response.body()

                return@withContext responseBody
            }
        }

        suspend fun getPredmetiZaKviz(kvizId: Int): List<Predmet>? {
            return withContext(Dispatchers.IO) {
                val rezultat = arrayListOf<Predmet>()
                val grupeZaKviz = getGrupeZaKviz(kvizId)
                val sviPredmeti = getPredmeti()

                if (grupeZaKviz == null || sviPredmeti == null) {
                    return@withContext emptyList<Predmet>()
                } else {
                    for (grupaZaKviz in grupeZaKviz) {
                        for (predmet in sviPredmeti) {
                            if (grupaZaKviz.idPredmeta == predmet.id) {
                                rezultat.add(predmet)
                            }
                        }
                    }
                    return@withContext rezultat
                }
            }
        }
    }
}
