package ba.etf.rma21.projekat.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Kviz(
    @SerializedName("id") val id: Int,
    @SerializedName("naziv") val naziv: String,
    val nazivPredmeta: String? = null,
    @SerializedName("datumPocetak") val datumPocetka: Date,
    @SerializedName("datumKraj") val datumKraj: Date?,
    val datumRada: Date? = null,
    @SerializedName("trajanje") val trajanje: Int,
    val nazivGrupe: String? = null,
    val osvojeniBodovi: Int? = null
) {
    var zavrsen: Boolean = false
    var prekinut: Boolean = false
}
