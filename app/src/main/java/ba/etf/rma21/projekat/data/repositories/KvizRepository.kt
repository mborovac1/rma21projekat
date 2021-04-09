package ba.etf.rma21.projekat.data.repositories

import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.mockupKvizes

class KvizRepository {
    companion object {
        // TODO: Implementirati
        init {
        }

        fun getMockupKvizes(): List<Kviz> {
            return mockupKvizes()
        }

        fun getMyKvizes(): List<Kviz> {
            // TODO: Implementirati
            return emptyList()
        }

        fun getAll(): List<Kviz> {
            // TODO: Implementirati
            return emptyList()
        }

        fun getDone(): List<Kviz> {
            // TODO: Implementirati
            return emptyList()
        }

        fun getFuture(): List<Kviz> {
            // TODO: Implementirati
            return emptyList()
        }

        fun getNotTaken(): List<Kviz> {
            // TODO: Implementirati
            return emptyList()
        }

        // TODO: Implementirati i ostale potrebne metode
    }
}
