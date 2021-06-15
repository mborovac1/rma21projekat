package ba.etf.rma21.projekat.data.models

import androidx.room.Entity

@Entity(primaryKeys = ["pitanjeId", "kvizId"])
data class PitanjeKviz(
    val pitanjeId: Int,
    val kvizId: Int
)
