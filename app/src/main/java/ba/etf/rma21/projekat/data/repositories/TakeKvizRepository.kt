package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.KvizTaken

class TakeKvizRepository {
    companion object {
        suspend fun zapocniKviz(idKviza: Int): KvizTaken? {
            return null
        }

        suspend fun getPocetiKvizovi(): List<KvizTaken>? {
            return emptyList()
        }
    }

}
