package ba.etf.rma21.projekat.data.repositories

import android.content.Context

class AccountRepository {
    companion object {
        var acHash: String = "31b01a25-4476-47b0-9418-c34fc2be4bba"
        private lateinit var context: Context

        fun setContext(_context: Context) {
            context = _context
        }

        fun postaviHash(acHash: String): Boolean {
            this.acHash = acHash
            return true
        }

        fun getHash(): String {
            return acHash
        }
    }
}
