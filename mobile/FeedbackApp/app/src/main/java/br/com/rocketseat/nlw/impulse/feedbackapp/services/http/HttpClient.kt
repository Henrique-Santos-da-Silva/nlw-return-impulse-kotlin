package br.com.rocketseat.nlw.impulse.feedbackapp.services.http

import br.com.rocketseat.nlw.impulse.feedbackapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HttpClient {
    private const val BASE_URL: String = BuildConfig.API_LOCAL_URL

    private fun httpClient(): OkHttpClient {
        val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.HOURS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    fun retrofit(): FeedbackApiEndPoint = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient())
        .build()
        .create(FeedbackApiEndPoint::class.java)
}