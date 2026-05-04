package psti.unram.tugasstorage.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MahasiswaDao {
    @Query("SELECT * FROM mahasiswa")
    fun getAll(): List<MahasiswaEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(mahasiswa: MahasiswaEntity)

    @Query("DELETE FROM mahasiswa")
    fun deleteAll()
}
