package psti.unram.tugasstorage

interface StorageMahasiswaInterface {
    fun save(mahasiswa: Mahasiswa)
    fun get(): Mahasiswa
    fun delete()
}
