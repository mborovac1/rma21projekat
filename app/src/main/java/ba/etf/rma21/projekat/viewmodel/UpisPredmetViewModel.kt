package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository

class UpisPredmetViewModel {
    suspend fun getPredmetiZaKviz(kvizId: Int): List<Predmet>? =
        PredmetIGrupaRepository.getPredmetiZaKviz(kvizId)

    suspend fun getNeupisaniPredmetiNazivi(godina: Int): List<String> =
        PredmetIGrupaRepository.getNeupisaniPredmetiNazivi(godina)

    suspend fun upisiUGrupu(idGrupa: Int): Boolean? = PredmetIGrupaRepository.upisiUGrupu(idGrupa)

    suspend fun getGrupaZaPredmet(idPredmeta: Int, nazivGrupe: String): Grupa =
        PredmetIGrupaRepository.getGrupaZaPredmet(idPredmeta, nazivGrupe)!!

    suspend fun getNeupisaneGrupeZaPredmet(idPredmeta: Int): List<Grupa> =
        PredmetIGrupaRepository.getNeupisaneGrupeZaPredmet(idPredmeta)

    suspend fun getPredmetByNaziv(nazivPredmeta: String): Predmet? =
        PredmetIGrupaRepository.getPredmetByNaziv(nazivPredmeta)
}
