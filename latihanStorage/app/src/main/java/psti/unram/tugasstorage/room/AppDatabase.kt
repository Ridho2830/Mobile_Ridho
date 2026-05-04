package psti.unram.tugasstorage.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MahasiswaEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mahasiswaDao(): MahasiswaDao
}