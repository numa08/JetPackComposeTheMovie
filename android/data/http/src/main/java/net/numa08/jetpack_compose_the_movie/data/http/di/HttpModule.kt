package net.numa08.jetpack_compose_the_movie.data.http.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import net.numa08.jetpack_compose_the_movie.data.http.BuildConfig
import net.numa08.jetpack_compose_the_movie.data.http.omdb.OMDbService
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

@InstallIn(SingletonComponent::class)
@Module
object HttpModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    fun providesOmdbService(): OMDbService {
        val httpClient = OkHttpClient
            .Builder()
            .addInterceptor {
                val url = it.request().url()
                    .newBuilder()
                    .addQueryParameter("apikey", BuildConfig.OMDB_API_KEY)
                    .build()
                val request = it.request()
                    .newBuilder()
                    .url(url)
                    .build()
                return@addInterceptor it.proceed(request)
            }
            .build()
        val contentType = MediaType.get("application/json; charset=utf-8")
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(Json.asConverterFactory(contentType))
            .client(httpClient)
            .build()
        return retrofit.create()
    }

}