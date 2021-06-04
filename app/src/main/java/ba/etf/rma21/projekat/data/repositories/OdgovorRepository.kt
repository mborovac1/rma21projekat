package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.GetOdgovorResponse
import ba.etf.rma21.projekat.data.models.KvizTaken
import ba.etf.rma21.projekat.data.models.Odgovor
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.viewmodel.PitanjeKvizViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.math.roundToInt

class OdgovorRepository {
    companion object {
        private val pitanjeKvizViewModel = PitanjeKvizViewModel()

        suspend fun getOdgovoriKviz(idKviza: Int): List<Odgovor>? {
            return withContext(Dispatchers.IO) {
                val pocetiKvizovi = TakeKvizRepository.getPocetiKvizovi()
                val odgovori = arrayListOf<Odgovor>()

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
                var brojTacnihOdgovora = 0
                val pocetiKvizovi = pitanjeKvizViewModel.getPocetiKvizovi()

                var mojKvizTaken = KvizTaken(0, "", 0, Date(), 0)

                for (kvizTaken in pocetiKvizovi) {
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
                    if (odgovorZaKviz.odgovoreno == svaPitanjaZaKviz.find { pitanje -> pitanje.id == odgovorZaKviz.PitanjeId }?.tacan) {
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
    }
}
