package psti.unram.tugasstorage

interface StorageMahasiswaInterface {
    suspend fun insert(mahasiswa: Mahasiswa)
    suspend fun getAll(): List<Mahasiswa>
    suspend fun delete(id: Int)
}
