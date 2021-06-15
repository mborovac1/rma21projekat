package ba.etf.rma21.projekat.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class PredmetSaGrupama(
    @Embedded val predmet: Predmet,
    @Relation(
        parentColumn = "id",
        entityColumn = "idPredmeta"
    )
    val grupe: List<Grupa>
)
