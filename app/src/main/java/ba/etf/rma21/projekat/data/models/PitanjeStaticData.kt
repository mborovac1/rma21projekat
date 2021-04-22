package ba.etf.rma21.projekat.data.models

fun pitanja(): List<Pitanje> {
    return listOf(
            Pitanje("IM-Pitanje1", "Koliko je 1 + 1?", listOf("3", "5", "2"), 2),
            Pitanje("IM-Pitanje2", "Koliko je 3 - 1?", listOf("4", "2", "1"), 1),
            Pitanje("IM-Pitanje3", "Koliko je 3 * 7?", listOf("21", "10", "14"), 0)
    )
}
