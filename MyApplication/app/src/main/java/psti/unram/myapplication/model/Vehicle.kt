package psti.unram.myapplication.model

data class Vehicle (
    val id: Int? = null,
    val model: String? = null,
    val type: String,
    val manufacturer: String,
    val created_at: String? = null,
    val updated_at: String? = null
)