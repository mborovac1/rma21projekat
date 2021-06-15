package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import android.util.Log
import ba.etf.rma21.projekat.data.AppDatabase
import ba.etf.rma21.projekat.data.models.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class DBRepository {
    companion object {
        private lateinit var context: Context

        fun setContext(_context: Context) {
            context = _context
        }

        suspend fun updateNow(): Boolean {
            return withContext(Dispatchers.IO) {
                val db = AppDatabase.getInstance(context)
                val trenutniAcc = db.accountDao().getTrenutniAccount()

                val response =
                    ApiAdapter.retrofit.imaLiPromjena(trenutniAcc.acHash, trenutniAcc.lastUpdate)
                val responseBody = response.body()

                if (responseBody == null) {
                    return@withContext false
                }

                if (responseBody.message != null && responseBody.message.contains("Ne postoji")) {
                    return@withContext false
                }

                if (responseBody.changed == true) {
                    // updateaj grupe, predmete, kvizove i pitanja
                    db.grupaDao().obrisiSve()
                    db.kvizDao().obrisiSve()
                    db.kvizTakenDao().obrisiSve()
                    db.odgovorDao().obrisiSve()
                    db.pitanjeDao().obrisiSve()
                    db.predmetDao().obrisiSve()

                    db.accountDao().obrisiSve()
                    db.accountDao()
                        .dodajAccount(Account(trenutniAcc.acHash, Calendar.getInstance().time.toString()))

                    val mojeGrupe = PredmetIGrupaRepository.getUpisaneGrupe()
                    val mojiPredmeti = PredmetIGrupaRepository.getUpisaniPredmeti()
                    val mojiKvizovi = KvizRepository.getMyKvizes()


                    /*for (predmet in mojiPredmeti) {
                        Log.v("naziv_predmeta", predmet.naziv!!)
                        Log.v("predmet_id", predmet.id.toString())
                    }*/


                    if (mojeGrupe != null) {
                        for (grupa in mojeGrupe) {
                            db.grupaDao().dodajGrupu(grupa)
                        }
                    }

                    for (predmet in mojiPredmeti) {
                        db.predmetDao().dodajPredmet(predmet)
                    }

                    for (kviz in mojiKvizovi) {
                        db.kvizDao().dodajKviz(kviz)
                        val pitanja = PitanjeKvizRepository.getPitanja(kviz.id)
                        if (pitanja != null) {
                            for (pitanje in pitanja) {
                                db.pitanjeDao().dodajPitanje(pitanje)
                            }
                        }

                        val odgovori = OdgovorRepository.getOdgovoriKviz(kviz.id)
                        for (odgovor in odgovori) {
                            db.odgovorDao().dodajOdgovor(odgovor)
                        }

                    }

                    // pitanja i odg i pitanjekviz

                    return@withContext true
                } else {
                    return@withContext false
                }
            }
        }
    }
}
