package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import ba.etf.rma21.projekat.data.AppDatabase
import ba.etf.rma21.projekat.data.models.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class AccountRepository {
    companion object {
        var acHash: String = "31b01a25-4476-47b0-9418-c34fc2be4bba"
        private lateinit var context: Context

        fun setContext(_context: Context) {
            context = _context
        }

        suspend fun postaviHash(acHash: String): Boolean {
            return withContext(Dispatchers.IO) {
                val db = AppDatabase.getInstance(context)
                val brojAccountova = db.accountDao().getBrojAccountova()

                if (acHash.equals(this@Companion.acHash)) {
                    return@withContext true
                }

                this@Companion.acHash = acHash

                if (brojAccountova != 0) {
                    db.accountDao().obrisiSve()
                }

                if (brojAccountova == 0) {
                    db.grupaDao().obrisiSve()
                    db.kvizDao().obrisiSve()
                    db.kvizTakenDao().obrisiSve()
                    db.odgovorDao().obrisiSve()
                    db.pitanjeDao().obrisiSve()
                    db.predmetDao().obrisiSve()

                    db.accountDao().dodajAccount(Account(acHash, Date().toString()))
                } else {
                    db.grupaDao().obrisiSve()
                    db.kvizDao().obrisiSve()
                    db.kvizTakenDao().obrisiSve()
                    db.odgovorDao().obrisiSve()
                    db.pitanjeDao().obrisiSve()
                    db.predmetDao().obrisiSve()

                    db.accountDao().dodajAccount(Account(acHash, Date().toString()))
                }

                return@withContext true
            }
        }

        fun getHash(): String = acHash
    }
}
