package psti.unram.tugaspasien.network

import psti.unram.tugaspasien.model.LoginRequest
import psti.unram.tugaspasien.model.LoginResponse
import psti.unram.tugaspasien.model.Pasien
import psti.unram.tugaspasien.model.PasienResponse
import psti.unram.tugaspasien.model.SinglePasienResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @GET("pasien")
    suspend fun getPasien(@Header("Authorization") token: String): Response<PasienResponse>

    @POST("pasien")
    suspend fun createPasien(
        @Header("Authorization") token: String,
        @Body pasien: Pasien
    ): Response<SinglePasienResponse>

    @PUT("pasien/{id}")
    suspend fun updatePasien(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body pasien: Pasien
    ): Response<SinglePasienResponse>

    @DELETE("pasien/{id}")
    suspend fun deletePasien(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<SinglePasienResponse>
}
