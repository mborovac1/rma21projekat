package ba.etf.rma21.projekat.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ba.etf.rma21.projekat.data.models.Account

@Dao
interface AccountDAO {
    @Query("DELETE FROM Account")
    suspend fun obrisiSve()

    @Insert
    suspend fun dodajAccount(vararg account: Account)

    @Query("SELECT * FROM Account LIMIT 1")
    suspend fun getTrenutniAccount(): Account

    @Query("SELECT COUNT(*) FROM Account")
    suspend fun getBrojAccountova(): Int
}
