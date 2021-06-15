package ba.etf.rma21.projekat.data.models

import com.google.gson.annotations.SerializedName

data class PromjenaResponse(
    @SerializedName("changed") val changed: Boolean,
    @SerializedName("message") val message: String?
)
