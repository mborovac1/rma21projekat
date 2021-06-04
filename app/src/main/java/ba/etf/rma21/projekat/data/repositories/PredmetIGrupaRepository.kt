package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.viewmodel.KvizListViewModel
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
                val poruka: String = response.message

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

        suspend fun getPredmetById(predmetId: Int): Predmet? {
            return withContext(Dispatchers.IO) {
                val response = ApiAdapter.retrofit.getPredmetById(predmetId)
                val responseBody = response.body()
                return@withContext responseBody
            }
        }

        suspend fun getPredmetByNaziv(nazivPredmeta: String): Predmet? {
            return withContext(Dispatchers.IO) {
                val sviPredmeti = getPredmeti()

                if (sviPredmeti != null) {
                    for (predmet in sviPredmeti) {
                        if (predmet.naziv.equals(nazivPredmeta)) {
                            return@withContext predmet
                        }
                    }
                }

                return@withContext null
            }
        }

        suspend fun getNeupisaneGrupeZaPredmet(idPredmeta: Int): List<Grupa> {
            return withContext(Dispatchers.IO) {
                val sveGrupeZaPredmet = getGrupeZaPredmet(idPredmeta)
                val upisaneGrupe = getUpisaneGrupe()
                val rezultat = arrayListOf<Grupa>()

                if (sveGrupeZaPredmet != null && upisaneGrupe != null) {
                    for (grupa in sveGrupeZaPredmet) {
                        if (!upisaneGrupe.contains(grupa)) {
                            rezultat.add(grupa)
                        }
                    }
                }
                return@withContext rezultat
            }
        }

        suspend fun getPredmetiByGodina(godina: Int): List<Predmet> {
            return withContext(Dispatchers.IO) {
                val predmeti = getPredmeti()
                val rezultat = arrayListOf<Predmet>()

                for (predmet in predmeti!!) {
                    if (predmet.godina == godina) {
                        rezultat.add(predmet)
                    }
                }

                return@withContext rezultat
            }
        }

        suspend fun getUpisaniPredmeti(): List<Predmet> {
            return withContext(Dispatchers.IO) {
                val kvizListViewModel = KvizListViewModel()
                val mojeGrupe = getUpisaneGrupe()
                val rezultat = arrayListOf<Predmet>()

                for (grupa in mojeGrupe!!) {
                    val kvizovi = kvizListViewModel.getKvizoviByGrupa(grupa.id)
                    for (kviz in kvizovi) {
                        val predmeti = getPredmetiZaKviz(kviz.id)
                        for (predmet in predmeti!!) {
                            rezultat.add(predmet)
                        }
                    }
                }

                return@withContext rezultat
            }
        }

        suspend fun getNeupisaniPredmetiNazivi(godina: Int): List<String> {
            return withContext(Dispatchers.IO) {
                val rezultat = arrayListOf<String>()
                val predmetiByGodina = getPredmetiByGodina(godina)
                val upisaniPredmeti = getUpisaniPredmeti()

                for (predmet in predmetiByGodina) {
                    if (!upisaniPredmeti.contains(predmet)) {
                        rezultat.add(predmet.naziv)
                    }
                }

                return@withContext rezultat
            }
        }

        suspend fun getGrupaZaPredmet(idPredmeta: Int, nazivGrupe: String): Grupa? {
            return withContext(Dispatchers.IO) {
                val grupeZaPredmet = getGrupeZaPredmet(idPredmeta)!!

                for (grupa in grupeZaPredmet) {
                    if (grupa.naziv.equals(nazivGrupe)) {
                        return@withContext grupa
                    }

                }
                return@withContext null

            }
        }
    }
}
