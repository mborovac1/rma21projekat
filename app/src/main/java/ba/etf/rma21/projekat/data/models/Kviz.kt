package ba.etf.rma21.projekat.data.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class Kviz(
    @PrimaryKey @SerializedName("id") val id: Int,
    @ColumnInfo(name = "naziv") @SerializedName("naziv") val naziv: String,
    val nazivPredmeta: String? = null,
    @ColumnInfo(name = "datumPocetka") @SerializedName("datumPocetak") val datumPocetka: Date,
    @ColumnInfo(name = "datumKraj") @SerializedName("datumKraj") val datumKraj: Date? = null,
    @ColumnInfo(name = "datumRada") val datumRada: Date? = null,
    @ColumnInfo(name = "trajanje") @SerializedName("trajanje") val trajanje: Int,
    val nazivGrupe: String? = null,
    val osvojeniBodovi: Int? = null
) : Parcelable {
    var zavrsen: Boolean = false
    var prekinut: Boolean = false

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString(),
        TODO("datumPocetka"),
        TODO("datumKraj"),
        TODO("datumRada"),
        parcel.readInt(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
        zavrsen = parcel.readByte() != 0.toByte()
        prekinut = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(naziv)
        parcel.writeString(nazivPredmeta)
        parcel.writeInt(trajanje)
        parcel.writeString(nazivGrupe)
        parcel.writeValue(osvojeniBodovi)
        parcel.writeByte(if (zavrsen) 1 else 0)
        parcel.writeByte(if (prekinut) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Kviz> {
        override fun createFromParcel(parcel: Parcel): Kviz {
            return Kviz(parcel)
        }

        override fun newArray(size: Int): Array<Kviz?> {
            return arrayOfNulls(size)
        }
    }
}
