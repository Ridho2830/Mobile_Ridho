package psti.unram.myapplication.network

import psti.unram.myapplication.model.Vehicle
import psti.unram.myapplication.model.ApiResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("vehicles")
    suspend fun getVehicles(): Response<ApiResponse<List<Vehicle>>>

    @POST("vehicles")
    suspend fun createVehicle(@Body vehicle: Vehicle): Response<ApiResponse<Vehicle>>

    @PUT("vehicles/{id}")
    suspend fun updateVehicle(@Path("id") id: Int, @Body vehicle: Vehicle): Response<ApiResponse<Vehicle>>

    @DELETE("vehicles/{id}")
    suspend fun deleteVehicle(@Path("id") id: Int): Response<ApiResponse<Vehicle>>
}
