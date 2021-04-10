package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.models.predmeti

class PredmetRepository {
    companion object {
        val sviPredmeti = predmeti()

        fun getUpisani(): List<Predmet> = KorisnikRepository.mojiPredmeti

        fun getAll(): List<Predmet> = sviPredmeti
    }
}
