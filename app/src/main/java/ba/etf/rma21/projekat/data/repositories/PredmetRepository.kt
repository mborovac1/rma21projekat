package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.models.predmeti

class PredmetRepository {
    companion object {
        val sviPredmeti = predmeti()

        fun getUpisani(): List<Predmet> = KorisnikRepository.getMojiPredmeti()

        fun getAll(): List<Predmet> = sviPredmeti

        private fun provjeriPredmet(predmet: Predmet): Boolean {
            for(p in KorisnikRepository.getMojiPredmeti())
                if (p.naziv.equals(predmet.naziv) && p.godina == predmet.godina)
                    return false
            return true
        }

        fun getNeupisani(godina: Int) : List<Predmet> {
            val listaPredmeta: ArrayList<Predmet> = arrayListOf()

            for (predmet in sviPredmeti)
                if (provjeriPredmet(predmet) && predmet.godina == godina)
                    listaPredmeta.add(predmet)

            return listaPredmeta
        }

        fun getNeupisaniNazivi(godina: Int): List<String> {
            val neupisaniPredmeti = getNeupisani(godina)
            val rezultat: ArrayList<String> = arrayListOf()

            neupisaniPredmeti.forEach{predmet -> rezultat.add(predmet.naziv)}

            return rezultat
        }
    }
}
