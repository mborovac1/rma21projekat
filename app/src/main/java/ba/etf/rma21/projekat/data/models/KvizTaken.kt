package ba.etf.rma21.projekat.data.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class KvizTaken(
    @PrimaryKey @SerializedName("id") val id: Int,
    @ColumnInfo(name = "student") @SerializedName("student") val student: String,
    @ColumnInfo(name = "osvojeniBodovi") @SerializedName("osvojeniBodovi") val osvojeniBodovi: Int?,
    @ColumnInfo(name = "datumRada") @SerializedName("datumRada") val datumRada: Date?,
    @SerializedName("KvizId") val KvizId: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        TODO("datumRada"),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(student)
        parcel.writeValue(osvojeniBodovi)
        parcel.writeInt(KvizId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<KvizTaken> {
        override fun createFromParcel(parcel: Parcel): KvizTaken {
            return KvizTaken(parcel)
        }

        override fun newArray(size: Int): Array<KvizTaken?> {
            return arrayOfNulls(size)
        }
    }
}
