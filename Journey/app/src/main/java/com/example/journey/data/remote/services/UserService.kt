import com.example.journey.data.models.AccessToken
import com.example.journey.data.models.Login
import com.example.journey.data.models.NewUserRequest
import com.example.journey.data.models.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UserService {
    @POST("users")
    suspend fun createUser(
        @Body newUser: NewUserRequest
    ): Response<UserResponse>

    @POST("auth/login")
    suspend fun login(
        @Body data: Login
    ): Response<AccessToken>

    @GET("users/me")
    suspend fun auth(@Header("Authorization") token: String): Response<UserResponse>
}