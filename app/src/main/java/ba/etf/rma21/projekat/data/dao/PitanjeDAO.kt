package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import ba.etf.rma21.projekat.data.models.Pitanje

@Dao
interface PitanjeDAO {
    @Query("DELETE FROM Pitanje")
    suspend fun obrisiSve()

    @Insert
    suspend fun dodajPitanje(pitanje: Pitanje)

    @Transaction
    @Query("SELECT * FROM Pitanje")
    suspend fun getPitanja(): List<Pitanje>
}
