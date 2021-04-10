package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.repositories.KvizRepository

class KvizListViewModel {
    fun getAll(): List<Kviz> = KvizRepository.getAll().sortedBy { kviz -> kviz.datumPocetka }

    fun getMojiKvizovi(): List<Kviz> {
        return KvizRepository.getMyKvizes().sortedBy { kviz -> kviz.datumPocetka }
    }

    fun getFuture(): List<Kviz> {
        return KvizRepository.getFuture().sortedBy { kviz -> kviz.datumPocetka }
    }

    fun getDone(): List<Kviz> = KvizRepository.getDone().sortedBy { kviz -> kviz.datumPocetka }

    fun getNotTaken(): List<Kviz> {
        return KvizRepository.getNotTaken().sortedBy { kviz -> kviz.datumPocetka }
    }
}
