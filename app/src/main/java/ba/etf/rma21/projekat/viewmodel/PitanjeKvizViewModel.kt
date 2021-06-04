package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.KvizTaken
import ba.etf.rma21.projekat.data.models.Odgovor
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.repositories.OdgovorRepository
import ba.etf.rma21.projekat.data.repositories.PitanjeKvizRepository
import ba.etf.rma21.projekat.data.repositories.TakeKvizRepository

class PitanjeKvizViewModel {
    fun getOdabraniKviz(): Kviz = PitanjeKvizRepository.odabraniKviz!!

    fun setOdabraniKviz(kviz: Kviz) {
        PitanjeKvizRepository.odabraniKviz = kviz
    }

    fun getOdabraniKvizTaken(): KvizTaken = TakeKvizRepository.odabraniKvizTaken!!

    fun setOdabraniKvizTaken(kvizTaken: KvizTaken) {
        TakeKvizRepository.odabraniKvizTaken = kvizTaken
    }

    suspend fun getPocetiKvizovi(): List<KvizTaken>? = TakeKvizRepository.getPocetiKvizovi()

    suspend fun getPitanja(idKviza: Int): List<Pitanje> =
        PitanjeKvizRepository.getPitanja(idKviza)!!

    suspend fun getOdgovoriKviz(idKviza: Int): List<Odgovor>? =
        OdgovorRepository.getOdgovoriKviz(idKviza)

    suspend fun postaviOdgovorKviz(idKvizTaken: Int, idPitanje: Int, odgovor: Int): Int =
        OdgovorRepository.postaviOdgovorKviz(idKvizTaken, idPitanje, odgovor)!!

    fun validacijaOdgovora(pozicija: Int, pitanje: Pitanje): Boolean = pozicija == pitanje.tacan

    suspend fun zapocniKviz(kid: Int) = TakeKvizRepository.zapocniKviz(kid)

    suspend fun getKvizTakenById(id: Int): KvizTaken? = TakeKvizRepository.getKvizTakenById(id)

    suspend fun getRezultatKviza(idKviza: Int): Int = TakeKvizRepository.getRezultatKviza(idKviza)

    suspend fun getMojOdgovorNaPitanje(idKviza: Int, idPitanja: Int): Int =
        TakeKvizRepository.getMojOdgovorNaPitanje(idKviza, idPitanja)
}
