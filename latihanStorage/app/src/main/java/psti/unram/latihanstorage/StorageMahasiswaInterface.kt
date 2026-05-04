package psti.unram.latihanstorage

interface StorageMahasiswaInterface {
    fun save(mahasiswa: Mahasiswa)
    fun get(): Mahasiswa
}
