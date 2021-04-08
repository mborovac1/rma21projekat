package ba.etf.rma21.projekat.data.models

import java.util.*

fun mockupKvizes(): List<Kviz> {
    return listOf(
            Kviz("Kviz 1", "RMA", Date(2021 - 1900, 4 - 1, 10), Date(2021 - 1900, 2, 3),
                    Date(2021 - 1900, 2, 3), 2, "Grupa 1", 1.5F),
            Kviz("Kviz 1", "DM", Date(2021 - 1900, 3 - 1, 10), Date(2021 - 1900, 2, 3),
                    Date(2021 - 1900, 2, 3), 5, "Grupa 2", 2.5F),
            Kviz("Kviz 1", "IM", Date(2021 - 1900, 5 - 1, 10), Date(2021 - 1900, 2, 3),
                    Date(2021 - 1900, 2, 3), 5, "Grupa 3", null),
            Kviz("Kviz 2", "RMA", Date(2021 - 1900, 4 - 1, 15), Date(2021 - 1900, 2, 3),
                    Date(2021 - 1900, 2, 3), 2, "Grupa 1", null),
            Kviz("Kviz 0", "RMA", Date(2021 - 1900, 3 - 1, 10), Date(2021 - 1900, 2, 3),
                    Date(2021 - 1900, 2, 3), 2, "Grupa 1", null)
    )
}
