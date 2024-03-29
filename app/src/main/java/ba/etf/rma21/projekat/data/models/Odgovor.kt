package ba.etf.rma21.projekat.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Odgovor(
    @PrimaryKey @SerializedName("id") var id: Int,
    @ColumnInfo(name = "odgovoreno") @SerializedName("odgovoreno") var odgovoreno: Int,
    @ColumnInfo(name = "KvizTakenId") @SerializedName("KvizTakenId") var idKvizTakena: Int,
    @ColumnInfo(name = "PitanjeId") @SerializedName("PitanjeId") var idPitanja: Int,
    @ColumnInfo(name = "KvizId") val idKviza: Int
) {
    companion object {
        var ID: Int = 0
    }
}
