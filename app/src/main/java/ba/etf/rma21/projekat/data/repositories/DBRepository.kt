package ba.etf.rma21.projekat.data.repositories

import android.content.Context

class DBRepository {
    companion object {
        private lateinit var context: Context

        fun setContext(_context: Context) {
            context = _context
        }

        suspend fun updateNow(): Boolean {
            return false
        }
    }
}
