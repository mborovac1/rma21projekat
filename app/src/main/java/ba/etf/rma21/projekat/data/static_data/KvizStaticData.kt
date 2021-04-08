package ba.etf.rma21.projekat.data.models

import java.util.*

fun mockupKvizes(): List<Kviz> {
    return listOf(
            Kviz("Kviz I", "RMA", Date(2021 - 1900, 4 - 1, 7), Date(2021 - 1900, 4 - 1, 17),
                    Date(2021 - 1900, 4 - 1, 10), 2, "Grupa 1", 1.5F),
            Kviz("Kviz I", "DM", Date(2021 - 1900, 3 - 1, 8), Date(2021 - 1900, 3 - 1, 15),
                    Date(2021 - 1900, 3 - 1, 10), 5, "Grupa 2", 2.5F),
            Kviz("Kviz I", "IM", Date(2021 - 1900, 5 - 1, 10), Date(2021 - 1900, 5 - 1, 17),
                    null, 5, "Grupa 3", null),
            Kviz("Kviz 2", "RMA", Date(2021 - 1900, 4 - 1, 7), Date(2021 - 1900, 4 - 1, 15),
                    null, 2, "Grupa 1", null),
            Kviz("Kviz O", "RMA", Date(2021 - 1900, 3 - 1, 10), Date(2021 - 1900, 3 - 1, 15),
                    null, 2, "Grupa 1", null)
    )
}
