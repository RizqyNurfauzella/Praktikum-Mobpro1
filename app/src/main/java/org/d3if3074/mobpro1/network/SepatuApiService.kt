package org.d3if3074.mobpro1.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.d3if3074.mobpro1.model.Sepatu
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://raw.githubusercontent.com/" +
        "RizqyNurfauzella/Sepatu/main/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface SepatuApiService {
    @GET("static-api.json")
    suspend fun getSepatu(): List<Sepatu>
}

object SepatuApi {
    val service: SepatuApiService by lazy {
        retrofit.create(SepatuApiService::class.java)
    }

    fun getSepatuUrl(imageId: String): String {
        return "$BASE_URL$imageId.png"
    }
}