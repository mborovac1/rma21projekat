package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Kviz

@Dao
interface KvizDAO {
    @Query("DELETE FROM Kviz")
    suspend fun obrisiSve()

    @Insert
    suspend fun dodajKviz(kviz: Kviz)
}
