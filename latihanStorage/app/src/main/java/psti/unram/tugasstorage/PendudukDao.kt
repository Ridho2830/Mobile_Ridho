package psti.unram.tugasstorage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PendudukDao {
    @Query("SELECT * FROM penduduk")
    fun getAll(): List<PendudukEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(penduduk: PendudukEntity)
}
