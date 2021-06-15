package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Predmet

@Dao
interface PredmetDAO {
    @Query("DELETE FROM Predmet")
    suspend fun obrisiSve()

    @Insert
    suspend fun dodajPredmet(predmet: Predmet)
}
