package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.Predmet

class KorisnikRepository {
    companion object {
        private var godinaStudija: Int
        private var mojiPredmeti: ArrayList<Predmet>
        private var mojeGrupe: ArrayList<Grupa>

        init {
            godinaStudija = 0
            mojiPredmeti = arrayListOf(Predmet("RMA", 2))
            mojeGrupe = arrayListOf(Grupa("RMA-Grupa1", "RMA"))
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
            val rezultat: ArrayList<Kviz> = arrayListOf()

            for (kviz in sviKvizovi)
                if (provjeriKviz(kviz))
                    rezultat.add(kviz)

            return rezultat
        }

        fun getMojiPredmeti(): List<Predmet> = mojiPredmeti

        fun getMojeGrupe(): List<Grupa> = mojeGrupe

        fun dodajPredmet(predmet: Predmet) = mojiPredmeti.add(predmet)

        fun dodajGrupu(grupa: Grupa) = mojeGrupe.add(grupa)

        fun getGodinaStudija(): Int = godinaStudija

        fun setGodinaStudija(godina: Int) {
            godinaStudija = godina
        }
    }
}
