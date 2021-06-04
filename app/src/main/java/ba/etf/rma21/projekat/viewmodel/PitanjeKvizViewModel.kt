package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.KvizTaken
import ba.etf.rma21.projekat.data.models.Odgovor
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.repositories.OdgovorRepository
import ba.etf.rma21.projekat.data.repositories.PitanjeKvizRepository
import ba.etf.rma21.projekat.data.repositories.TakeKvizRepository

class PitanjeKvizViewModel {
    suspend fun getPocetiKvizovi(): List<KvizTaken> = TakeKvizRepository.getPocetiKvizovi()!!

    suspend fun getPitanja(idKviza: Int): List<Pitanje> =
        PitanjeKvizRepository.getPitanja(idKviza)!!

    suspend fun getOdgovoriKviz(idKviza: Int): List<Odgovor> =
        OdgovorRepository.getOdgovoriKviz(idKviza)!!

    suspend fun postaviOdgovorKviz(idKvizTaken: Int, idPitanje: Int, odgovor: Int): Int =
        OdgovorRepository.postaviOdgovorKviz(idKvizTaken, idPitanje, odgovor)!!

    /*
    fun getPitanja(idKviza: Int): List<Pitanje>? {
        scope.launch {
            val rezultat = PitanjeKvizRepository.getPitanja(idKviza)



        }
    }

    fun getPitanja(nazivKviza: String, nazivPredmeta: String): List<Pitanje> {
        return PitanjeKvizRepository.getPitanja(nazivKviza, nazivPredmeta)
    }

    fun postaviOdgovor(nazivPitanja: String, nazivKviza: String, nazivPredmeta: String,
                       pozicija: Int) {
        PitanjeKvizRepository.postaviOdgovor(nazivPitanja, nazivKviza, nazivPredmeta, pozicija)
    }

    fun getOdgovorZaPitanje(nazivPitanja: String, nazivKviza: String, nazivPredmeta: String): Int {
        return PitanjeKvizRepository.getOdgovorZaPitanje(nazivPitanja, nazivKviza, nazivPredmeta)
    }

    fun getRezultatKviza(nazivKviza: String, nazivPredmeta: String): Double {
        return PitanjeKvizRepository.getRezultatKviza(nazivKviza, nazivPredmeta)
    }
    */

    fun getOdabraniKviz(): Kviz = PitanjeKvizRepository.odabraniKviz!!

    fun setOdabraniKviz(kviz: Kviz) {
        PitanjeKvizRepository.odabraniKviz = kviz
    }

    fun validacijaOdgovora(pozicija: Int, pitanje: Pitanje): Boolean = pozicija == pitanje.tacan

}
