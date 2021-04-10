package ba.etf.rma21.projekat.data.models

fun predmeti(): List<Predmet> {
    return listOf(
            Predmet("IM", 1),
            Predmet("MLTI", 1),
            Predmet("TP", 1),

            Predmet("RMA", 2),
            Predmet("DM", 2),
            Predmet("AFJ", 2),

            Predmet("WT", 3),
            Predmet("OOI", 3),
            Predmet("VVS", 3),

            Predmet("PRS", 4),
            Predmet("NASP", 4),
            Predmet("RM", 4),

            Predmet("RSRV", 5),
            Predmet("NSI", 5),
            Predmet("TS", 5)
    )
}
