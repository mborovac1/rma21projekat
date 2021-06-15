package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface KvizDAO {
    @Query("DELETE FROM Kviz")
    suspend fun obrisiSve()
}
