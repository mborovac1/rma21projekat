package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.repositories.GrupaRepository
import ba.etf.rma21.projekat.data.repositories.KorisnikRepository
import ba.etf.rma21.projekat.data.repositories.PredmetRepository

class UpisPredmetViewModel {
    fun getAll(): List<Predmet> =  PredmetRepository.getAll()

    fun getUpisani(): List<Predmet> = PredmetRepository.getUpisani()

    fun getNeupisani(godina: Int): List<String> {
        val neupisaniPredmeti = PredmetRepository.getNeupisani(godina)
        val rezultat: ArrayList<String> = arrayListOf()

        neupisaniPredmeti.forEach{predmet -> rezultat.add(predmet.naziv)}

        return rezultat
    }

    fun getGroupsByPredmet(nazivPredmeta: String): List<String> {
        val sveGrupe = GrupaRepository.sveGrupe
        val rezultat: ArrayList<String> = arrayListOf()

        for (grupa in sveGrupe)
            if (grupa.nazivPredmeta.equals(nazivPredmeta))
                rezultat.add(grupa.naziv)

        return rezultat
    }

    fun upisiPredmet(nazivPredmeta: String, nazivGrupe: String, godinaPredmeta: String) {
        KorisnikRepository.dodajPredmet(Predmet(nazivPredmeta, godinaPredmeta.toInt()))
        KorisnikRepository.dodajGrupu(Grupa(nazivGrupe, nazivPredmeta))
    }
}
