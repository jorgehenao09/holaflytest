package com.jh.holaflytest.di

import com.jh.holaflytest.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Dispatcher
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by Jorge Henao on 10/11/23.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val authInterceptor = { chain: Interceptor.Chain ->
        val ts = System.currentTimeMillis()

        val hash =
            "$ts${BuildConfig.MARVEL_PRIVATE_API_KEY}${BuildConfig.MARVEL_PUBLIC_API_KEY}".md5()
        val request = chain.request()
        val url = request.url
            .newBuilder()
            .addQueryParameter("ts", ts.toString())
            .addQueryParameter("apikey", BuildConfig.MARVEL_PUBLIC_API_KEY)
            .addQueryParameter("hash", hash)
            .build()
        val updated = request.newBuilder()
            .url(url)
            .build()

        chain.proceed(updated)
    }

    private val userAgentInterceptor = { chain: Interceptor.Chain ->
        chain.proceed(
            chain.request().newBuilder()
                .addHeader("User-Agent", "Superheroes app/${BuildConfig.VERSION_CODE}")
                .build()
        )
    }

    /**
     * An own OkHttpClient for the main flow of the app
     */
    @Provides
    @Singleton
    fun okHttpProvider(): OkHttpClient = OkHttpClient.Builder()
        .dispatcher(Dispatcher().apply { maxRequests = MAX_REQUEST })
        .addInterceptor(authInterceptor)
        .addInterceptor(userAgentInterceptor)
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .apply {
            addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
        }
        .build()

    @Provides
    @Singleton
    fun retrofitProvider(
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl(BuildConfig.MARVEL_BASE_URL)
            .build()
    }
}

private fun String.md5(): String =
    BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
        .toString(16).padStart(32, '0')

private const val MAX_REQUEST = 1
private const val TIMEOUT = 50L
