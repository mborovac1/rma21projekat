package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Kviz
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class KvizRepository {
    companion object {
        private val danasnjiDatum: Date = Calendar.getInstance().time

        suspend fun getKvizoviByGrupa(idGrupe: Int): List<Kviz>? {
            return withContext(Dispatchers.IO) {
                val response = ApiAdapter.retrofit.getKvizoviByGrupa(idGrupe)
                val responseBody = response.body()
                return@withContext responseBody
            }
        }

        suspend fun getMyKvizes(): List<Kviz> {
            return withContext(Dispatchers.IO) {
                val tempList = ArrayList<Kviz>(0)
                for (grupa in PredmetIGrupaRepository.getUpisaneGrupe()!!) {
                    for (kviz in getKvizoviByGrupa(grupa.id)!!) {
                        tempList.add(kviz)
                    }
                }
                return@withContext tempList
            }
        }

        suspend fun getDone(): List<Kviz> {
            return withContext(Dispatchers.IO) {
                val rezultat = arrayListOf<Kviz>()
                val mojiKvizovi = getMyKvizes()

                for (kviz in mojiKvizovi) {
                    if (kviz.osvojeniBodovi != null) {
                        rezultat.add(kviz)
                    }
                }
                return@withContext rezultat

            }
        }

        suspend fun getFuture(): List<Kviz> {
            return withContext(Dispatchers.IO) {
                val rezultat = arrayListOf<Kviz>()
                val mojiKvizovi = getMyKvizes()

                for (kviz in mojiKvizovi) {
                    if (kviz.datumPocetka.after(danasnjiDatum)) {
                        rezultat.add(kviz)
                    }
                }
                return@withContext rezultat

            }
        }

        suspend fun getNotTaken(): List<Kviz> {
            return withContext(Dispatchers.IO) {
                val rezultat = arrayListOf<Kviz>()
                val mojiKvizovi = getMyKvizes()

                for (kviz in mojiKvizovi) {
                    if (kviz.datumPocetka.before(danasnjiDatum) &&
                        kviz.datumRada == null &&
                        kviz.osvojeniBodovi == null
                    ) {
                        rezultat.add(kviz)
                    }
                }

                return@withContext rezultat

            }
        }

        suspend fun getAll(): List<Kviz>? {
            return withContext(Dispatchers.IO) {
                val response = ApiAdapter.retrofit.getKvizovi()
                val responseBody = response.body()
                return@withContext responseBody
            }
        }

        suspend fun getById(id: Int): Kviz? {
            return withContext(Dispatchers.IO) {
                val response = ApiAdapter.retrofit.getKvizById(id)
                val responseBody = response.body()
                return@withContext responseBody
            }
        }

        suspend fun getUpisani(): List<Kviz>? {
            return withContext(Dispatchers.IO) {
                return@withContext getMyKvizes()
            }
        }
    }
}
