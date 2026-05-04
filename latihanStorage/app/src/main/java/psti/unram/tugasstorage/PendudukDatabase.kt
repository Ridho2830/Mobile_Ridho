package psti.unram.tugasstorage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PendudukEntity::class], version = 1)
abstract class PendudukDatabase : RoomDatabase() {
    abstract fun pendudukDao(): PendudukDao
}
