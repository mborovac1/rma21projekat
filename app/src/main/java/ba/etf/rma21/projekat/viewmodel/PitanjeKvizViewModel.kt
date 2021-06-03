package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.Pitanje
import ba.etf.rma21.projekat.data.repositories.PitanjeKvizRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
/*
class PitanjeKvizViewModel {

    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun getPitanja(idKviza: Int): List<Pitanje>? {
        scope.launch {
            val rezultat = PitanjeKvizRepository.getPitanja(idKviza)



        }
    }


    fun getPitanja(nazivKviza: String, nazivPredmeta: String): List<Pitanje> {
        return PitanjeKvizRepository.getPitanja(nazivKviza, nazivPredmeta)
    }

    fun validacijaOdgovora(pozicija: Int, pitanje: Pitanje): Boolean = pozicija == pitanje.tacan

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

    fun getOdabraniKviz(): Kviz = PitanjeKvizRepository.odabraniKviz!!

    fun setOdabraniKviz(kviz: Kviz) {
        PitanjeKvizRepository.odabraniKviz = kviz
    }

} */
