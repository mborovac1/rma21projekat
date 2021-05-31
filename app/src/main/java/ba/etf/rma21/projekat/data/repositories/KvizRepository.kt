package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Kviz

class KvizRepository {
    companion object {
        init {

        }

        fun getMyKvizes(): List<Kviz> {
            return emptyList()
        }

        fun getAl1l(): List<Kviz> {
            return emptyList()
        }

        fun getDone(): List<Kviz> {
            return emptyList()
        }

        fun getFuture(): List<Kviz> {
            return emptyList()
        }

        fun getNotTaken(): List<Kviz> {
            return emptyList()
        }

        suspend fun getAll(): List<Kviz>? {
            return emptyList()
        }

        suspend fun getById(id: Int): Kviz? {
            return null
        }

        suspend fun getUpisani(): List<Kviz>? {
            return emptyList()
        }
    }
}
