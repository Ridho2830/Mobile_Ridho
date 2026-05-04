package psti.unram.tugasstorage

class Mahasiswa(
    var nim: String? = "",
    var nama: String? = ""
) {
    fun isAvailable(): Boolean {
        return !this.nim.isNullOrEmpty()
    }
}
