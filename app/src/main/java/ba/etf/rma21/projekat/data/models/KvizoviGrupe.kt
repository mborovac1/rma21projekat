package ba.etf.rma21.projekat.data.models

import androidx.room.Entity

@Entity(primaryKeys = ["grupaId", "kvizId"])
data class KvizoviGrupe(
    val grupaId: Int,
    val kvizId: Int
)
