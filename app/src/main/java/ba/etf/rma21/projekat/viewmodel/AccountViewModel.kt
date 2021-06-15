package ba.etf.rma21.projekat.viewmodel

import android.content.Context
import ba.etf.rma21.projekat.data.repositories.*

class AccountViewModel {
    suspend fun postaviHash(acHash: String): Boolean =
        AccountRepository.postaviHash(acHash)

    suspend fun getHash(): String = AccountRepository.getHash()

    suspend fun postaviContext(context: Context) {
        AccountRepository.setContext(context)
        DBRepository.setContext(context)
        KvizRepository.setContext(context)
        OdgovorRepository.setContext(context)
        PitanjeKvizRepository.setContext(context)
        PredmetIGrupaRepository.setContext(context)
        TakeKvizRepository.setContext(context)
    }
}
