package ba.etf.rma21.projekat.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class KvizTakenSaOdgovorima(
    @Embedded val kvizTaken: KvizTaken,
    @Relation(
        parentColumn = "id",
        entityColumn = "idKvizTakena"
    )
    val odgovori: List<Odgovor>
)
