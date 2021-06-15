package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.KvizTaken

@Dao
interface KvizTakenDAO {
    @Query("DELETE FROM KvizTaken")
    suspend fun obrisiSve()

    @Insert
    suspend fun dodajKvizTaken(kvizTaken: KvizTaken)
}
