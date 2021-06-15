package ba.etf.rma21.projekat.data.repositories

import android.content.Context
import ba.etf.rma21.projekat.data.AppDatabase
import ba.etf.rma21.projekat.data.models.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class DBRepository {
    companion object {
        private lateinit var context: Context

        fun setContext(_context: Context) {
            context = _context
        }

        suspend fun updateNow(): Boolean {
            return withContext(Dispatchers.IO) {
                val db = AppDatabase.getInstance(context)
                val trenutniAcc = db.accountDao().getTrenutniAccount()

                val response =
                    ApiAdapter.retrofit.imaLiPromjena(trenutniAcc.acHash, trenutniAcc.lastUpdate)
                val responseBody = response.body()

                if (responseBody == null) {
                    return@withContext false
                }

                if (responseBody.message != null && responseBody.message.contains("Ne postoji")) {
                    return@withContext false
                }

                if (responseBody.changed == true) {
                    // updateaj grupe, predmete, kvizove i pitanja

                    db.accountDao().obrisiSve()
                    db.accountDao().dodajAccount(
                        Account(
                            trenutniAcc.acHash,
                            Calendar.getInstance().time.toString()
                        )
                    )

                    return@withContext true
                } else {
                    return@withContext false
                }
            }
        }
    }
}
