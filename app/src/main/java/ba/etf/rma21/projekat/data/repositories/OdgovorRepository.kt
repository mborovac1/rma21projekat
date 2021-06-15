package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import ba.etf.rma21.projekat.data.AppDatabase
import ba.etf.rma21.projekat.data.models.Odgovor
import ba.etf.rma21.projekat.viewmodel.PitanjeKvizViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class OdgovorRepository {
    companion object {
        private val pitanjeKvizViewModel = PitanjeKvizViewModel()
        private lateinit var context: Context

        fun setContext(_context: Context) {
            context = _context
        }


        suspend fun getOdgovoriKviz(idKviza: Int): List<Odgovor> {
            return withContext(Dispatchers.IO) {
                val pocetiKvizovi = TakeKvizRepository.getPocetiKvizovi()
                val odgovori = arrayListOf<Odgovor>()

                if (pocetiKvizovi.isNullOrEmpty())
                    return@withContext emptyList<Odgovor>()

                for (kvizTaken in pocetiKvizovi!!) {
                    if (kvizTaken.KvizId == idKviza) {
                        odgovori.addAll(
                            ApiAdapter.retrofit
                                .getOdgovoriKviz(AccountRepository.acHash, kvizTaken.id).body()!!
                        )
                    }
                }

                return@withContext odgovori
            }
        }

        suspend fun postaviOdgovorKviz(idKvizTaken: Int, idPitanje: Int, odgovor: Int): Int? {
            return withContext(Dispatchers.IO) {
                val db = AppDatabase.getInstance(context)

                val mojiOdgovori = db.odgovorDao().getMojiOdgovoriZaKvizTaken(idKvizTaken)

                var nadjen: Boolean = false
                for (odg in mojiOdgovori) {
                    if (odg.idKvizTakena == idKvizTaken && odg.idPitanja == idPitanje) {
                        nadjen = true
                        break
                    }
                }

                if (!nadjen) {
                    val id = Odgovor.ID
                    var odgovor = Odgovor(id, odgovor, idKvizTaken, idPitanje)
                    db.odgovorDao().dodajOdgovor(odgovor)
                    Odgovor.ID = Odgovor.ID + 1
                }

                return@withContext 50 // izračunati fino postotak
            }
        }

        /*
        suspend fun postaviOdgovorKviz(idKvizTaken: Int, idPitanje: Int, odgovor: Int): Int? {
            return withContext(Dispatchers.IO) {
                var brojTacnihOdgovora = 0
                val pocetiKvizovi = pitanjeKvizViewModel.getPocetiKvizovi()

                var mojKvizTaken = KvizTaken(0, "", 0, Date(), 0)

                for (kvizTaken in pocetiKvizovi!!) {
                    if (kvizTaken.id == idKvizTaken) {
                        mojKvizTaken = kvizTaken // iz ovog sad dobijemo kviz
                        break
                    }
                }

                val svaPitanjaZaKviz = pitanjeKvizViewModel.getPitanja(mojKvizTaken.KvizId)
                var mojePitanje = Pitanje(0, "", "", listOf(), 0)

                for (pitanje in svaPitanjaZaKviz) {
                    if (pitanje.id == idPitanje) {
                        mojePitanje = pitanje
                        break
                    }
                }

                for (odgovorZaKviz in getOdgovoriKviz(mojKvizTaken.KvizId)!!) {
                    if (odgovorZaKviz.odgovoreno == svaPitanjaZaKviz.find { pitanje -> pitanje.id == odgovorZaKviz.idPitanja }?.tacan) {
                        brojTacnihOdgovora++
                    }
                }

                if (odgovor == mojePitanje.tacan) {
                    brojTacnihOdgovora++
                }

                val prosjek =
                    ((brojTacnihOdgovora.toDouble() * 100) / svaPitanjaZaKviz.size.toDouble())
                        .roundToInt()

                ApiAdapter.retrofit.postaviOdgovorKviz(
                    AccountRepository.acHash,
                    idKvizTaken, odgovorResponse = GetOdgovorResponse(odgovor, idPitanje, prosjek)
                ).body()


                return@withContext prosjek

            }
        }
         */
    }
}
