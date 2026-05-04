package psti.unram.tugasstorage.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy

@Dao
interface MahasiswaDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: MahasiswaEntity)

    @Query("SELECT * FROM mahasiswa ORDER BY id DESC")
    suspend fun getAll(): List<MahasiswaEntity>

    @Query("DELETE FROM mahasiswa WHERE id = :id")
    suspend fun delete(id: Int)

}