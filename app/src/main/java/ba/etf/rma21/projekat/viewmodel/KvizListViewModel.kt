package ba.etf.rma21.projekat.viewmodel

import ba.etf.rma21.projekat.data.models.Grupa
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.Predmet
import ba.etf.rma21.projekat.data.repositories.KvizRepository
import ba.etf.rma21.projekat.data.repositories.PredmetIGrupaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class KvizListViewModel {
    val scope = CoroutineScope(Job() + Dispatchers.Main)

    fun getAll(onSuccess: (kvizovi: List<Kviz>) -> Unit, onError: () -> Unit) {
        scope.launch {
            val result = KvizRepository.getAll()

            when (result) {
                is List<Kviz> -> onSuccess?.invoke(result)
                else -> onError?.invoke()
            }
        }
    }

    fun getMojiKvizovi(onSuccess: (kvizovi: List<Kviz>) -> Unit, onError: () -> Unit) {
        scope.launch {
            val result = KvizRepository.getMyKvizes()

            when (result) {
                is List<Kviz> -> onSuccess?.invoke(result)
                else -> onError?.invoke()
            }
        }
    }

    fun getFuture(onSuccess: (kvizovi: List<Kviz>) -> Unit, onError: () -> Unit) {
        scope.launch {
            val result = KvizRepository.getFuture()

            when (result) {
                is List<Kviz> -> onSuccess?.invoke(result)
                else -> onError?.invoke()
            }
        }
    }

    fun getDone(onSuccess: (kvizovi: List<Kviz>) -> Unit, onError: () -> Unit) {
        scope.launch {
            val result = KvizRepository.getDone()

            when (result) {
                is List<Kviz> -> onSuccess?.invoke(result)
                else -> onError?.invoke()
            }
        }
    }

    fun getNotTaken(onSuccess: (kvizovi: List<Kviz>) -> Unit, onError: () -> Unit) {
        scope.launch {
            val result = KvizRepository.getNotTaken()

            when (result) {
                is List<Kviz> -> onSuccess?.invoke(result)
                else -> onError?.invoke()
            }
        }
    }

    suspend fun getPredmetiZaKviz(kvizId: Int): List<Predmet>? = PredmetIGrupaRepository.getPredmetiZaKviz(kvizId)
}
