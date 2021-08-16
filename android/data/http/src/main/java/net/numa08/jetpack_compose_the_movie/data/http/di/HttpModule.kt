package net.numa08.jetpack_compose_the_movie.data.http.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import net.numa08.jetpack_compose_the_movie.data.http.BuildConfig
import net.numa08.jetpack_compose_the_movie.data.http.omdb.OMDbService
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HttpModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Singleton
    @Provides
    fun providesOmdbService(@ApplicationContext @Suppress("UNUSED_PARAMETER") context: Context): OMDbService {
//        val cache = Cache(context.cacheDir, (5 * 1024 * 1024).toLong())
        val httpClient = OkHttpClient
            .Builder()
//            .cache(cache)
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
//            .addInterceptor {
//                val request = it
//                    .request()
//                    .newBuilder()
//                    .header(
//                        "Cache-Control",
//                        "public, only-if-cached, max-stale=${60 * 60 * 24 * 7}"
//                    )
//                    .build()
//                it.proceed(request)
//            }
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