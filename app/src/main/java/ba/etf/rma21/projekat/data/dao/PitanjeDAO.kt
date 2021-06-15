package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PitanjeDAO {
    @Query("DELETE FROM Pitanje")
    suspend fun obrisiSve()
}
