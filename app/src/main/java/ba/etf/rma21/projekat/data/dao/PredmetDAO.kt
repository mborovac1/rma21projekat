package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PredmetDAO {
    @Query("DELETE FROM Predmet")
    suspend fun obrisiSve()
}
