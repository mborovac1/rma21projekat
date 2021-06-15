package ba.etf.rma21.projekat.data.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class GrupeSaKvizovima(
    @Embedded val grupa: Grupa,
    @Relation(
        parentColumn = "grupaId",
        entityColumn = "kvizId",
        associateBy = Junction(KvizoviGrupe::class)
    )
    val kvizovi: List<Kviz>
)
