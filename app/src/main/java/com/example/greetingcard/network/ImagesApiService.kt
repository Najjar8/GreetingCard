package com.example.greetingcard.network


import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import kotlinx.serialization.json.Json


private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"

private val json = Json { ignoreUnknownKeys = true }
private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()


interface ImagesApiService {
    @GET("photos")
    suspend fun getPhotos(): List<GalleryPhoto>
}

object ImgApi {

    val retrofitService : ImagesApiService by lazy {
        retrofit.create(ImagesApiService::class.java)
    }

}

