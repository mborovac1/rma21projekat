package ba.etf.rma21.projekat.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class PitanjaSaOdgovorima(
    @Embedded val pitanje: Pitanje,
    @Relation(
        parentColumn = "id",
        entityColumn = "idPitanja"
    )
    val odgovori: List<Odgovor>
)
