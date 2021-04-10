package ba.etf.rma21.projekat.data.models

// samo je jedan korisnik za sada ...
data class Korisnik (
        val godinaStudija: Int, val mojiPredmeti: List<Kviz>, val mojeGrupe: List<Grupa>
 )
