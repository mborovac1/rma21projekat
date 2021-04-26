package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.repositories.GrupaRepository
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository
import ba.etf.rma21.projekat.data.repositories.PredmetRepository

class UpisPredmetViewModel {
    fun getAll(): List<Predmet> =  PredmetRepository.getAll()

    fun getUpisani(): List<Predmet> = PredmetRepository.getUpisani()

    fun getNeupisaniNazivi(godina: Int): List<String> {
        return PredmetRepository.getNeupisaniNazivi(godina)
    }

    fun getNaziviGroupaZaPredmet(nazivPredmeta: String): List<String> {
        return GrupaRepository.getNaziviGroupaZaPredmet(nazivPredmeta)
    }

    fun upisiPredmet(nazivPredmeta: String, nazivGrupe: String, godinaPredmeta: String) {
        KorisnikRepository.dodajPredmet(Predmet(nazivPredmeta, godinaPredmeta.toInt()))
        KorisnikRepository.dodajGrupu(Grupa(nazivGrupe, nazivPredmeta))
    }
}
