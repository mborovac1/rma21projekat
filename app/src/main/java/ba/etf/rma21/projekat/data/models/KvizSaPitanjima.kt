package ba.etf.rma21.projekat.data.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class KvizSaPitanjima(
    @Embedded val kviz: Kviz,
    @Relation(
        parentColumn = "kvizId",
        entityColumn = "pitanjeId",
        associateBy = Junction(PitanjeKviz::class)
    )
    val pitanja: List<Pitanje>
)
