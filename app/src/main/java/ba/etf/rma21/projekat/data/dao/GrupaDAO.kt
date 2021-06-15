package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Grupa

@Dao
interface GrupaDAO {
    @Query("DELETE FROM Grupa")
    suspend fun obrisiSve()

    @Insert
    suspend fun dodajGrupu(grupa: Grupa)
}
