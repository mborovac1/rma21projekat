package ba.etf.rma21.projekat.data.models

fun pitanjaZaKviz(): List<PitanjeKviz> {
    return listOf(
            PitanjeKviz("IM-Pitanje1", "Kviz 1", Predmet("IM", 1)),
            PitanjeKviz("IM-Pitanje2", "Kviz 1", Predmet("IM", 1)),
            PitanjeKviz("IM-Pitanje3", "Kviz 1", Predmet("IM", 1))
    )
}
