package psti.unram.tugaspasien.model

data class LoginResponse(
    val success: Boolean?,
    val message: String?,
    val data: LoginData?
)

data class LoginData(
    val user: User?,
    val token: String?
)

data class User(
    val id: Int?,
    val name: String?,
    val email: String?
)
