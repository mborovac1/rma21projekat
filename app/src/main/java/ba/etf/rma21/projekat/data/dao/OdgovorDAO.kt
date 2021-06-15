package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Odgovor

@Dao
interface OdgovorDAO {
    @Query("DELETE FROM Odgovor")
    suspend fun obrisiSve()

    @Insert
    suspend fun dodajOdgovor(vararg odgovor: Odgovor)

    @Query("SELECT MAX(id)+1 FROM Odgovor")
    suspend fun getSljedeciId(): Long

    @Query("SELECT * FROM Odgovor WHERE idKvizTakena=:ktId")
    suspend fun getMojiOdgovoriZaKvizTaken(ktId: Int): List<Odgovor>
}
