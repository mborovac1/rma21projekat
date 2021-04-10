package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.Predmet

class KorisnikRepository {
    companion object {
        val godinaStudija: Int
        val mojiPredmeti: List<Predmet>
        val mojeGrupe: List<Grupa>

        init {
            godinaStudija = 0
            mojiPredmeti = mutableListOf(Predmet("RMA", 2))
            mojeGrupe = mutableListOf(Grupa("RMA-Grupa1", "RMA"))
        }

        private fun provjeriKviz(kviz: Kviz): Boolean {
            for (grupa in mojeGrupe)
                if (grupa.naziv.equals(kviz.nazivGrupe) &&
                    grupa.nazivPredmeta.equals(kviz.nazivPredmeta))
                        return true
            return false
        }

        fun getMojiKvizovi(): List<Kviz> {
            val sviKvizovi = KvizRepository.getAll()
            var rezultat: List<Kviz> = mutableListOf()

            for (kviz in sviKvizovi) {
                if (provjeriKviz(kviz))
                    rezultat += kviz
            }

            return rezultat
        }
    }
}
