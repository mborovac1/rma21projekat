package ba.etf.rma21.projekat.data.repositories

class AccountRepository {
    companion object {
        var acHash: String = "31b01a25-4476-47b0-9418-c34fc2be4bba"

        fun postaviHash(acHash: String): Boolean {
            this.acHash = acHash
            return true
        }

        fun getHash(): String = acHash
    }
}
