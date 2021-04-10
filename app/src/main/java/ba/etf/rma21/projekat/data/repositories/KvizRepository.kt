package ba.etf.rma21.projekat.data.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import ba.etf.rma21.projekat.data.models.Kviz
import ba.etf.rma21.projekat.data.models.kvizovi
import java.util.*

class KvizRepository {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        val sviKvizovi = kvizovi()

        fun getAll(): List<Kviz> = sviKvizovi

        fun getMyKvizes(): List<Kviz> = KorisnikRepository.getMojiKvizovi()

        fun getDone(): List<Kviz> {
            var listaKvizova: List<Kviz> = mutableListOf()
            for (kviz in getMyKvizes())
                if (kviz.datumRada != null)
                    listaKvizova += kviz
            return listaKvizova
        }

        fun getFuture(): List<Kviz> {
            val danasnjiDatum: Date = Calendar.getInstance().time
            var listaKvizova: List<Kviz> = mutableListOf()

            for (kviz in getMyKvizes())
                if (kviz.datumPocetka.after(danasnjiDatum))
                    listaKvizova += kviz

            return listaKvizova
        }

        fun getNotTaken(): List<Kviz> {
            val danasnjiDatum: Date = Calendar.getInstance().time
            var listaKvizova: List<Kviz> = mutableListOf()

            for (kviz in getMyKvizes())
                if (kviz.datumPocetka.before(danasnjiDatum) &&
                    kviz.datumKraj.before(danasnjiDatum) &&
                    kviz.datumRada == null &&
                    kviz.osvojeniBodovi == null)
                        listaKvizova += kviz

            return listaKvizova
        }
    }
}
