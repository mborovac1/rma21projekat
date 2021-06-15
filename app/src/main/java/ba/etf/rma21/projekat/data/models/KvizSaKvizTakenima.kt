package ba.etf.rma21.projekat.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class KvizSaKvizTakenima(
    @Embedded val kviz: Kviz,
    @Relation(
        parentColumn = "id",
        entityColumn = "KvizId"
    )
    val kvizoviTaken: List<KvizTaken>
)
