package app.itsyour.weathercrane

import app.itsyour.weathercrane.service.WeatherService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
class AppModule {
    val APP_ID_KEY = "appid"
    val APP_ID_VALUE = "7c8b5c9429e2507f8d21a248c577ba7b"

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag("OkHttp").d(message) }
        })
        .apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
        .apply { redactHeader(APP_ID_KEY) }

    @Singleton
    @Provides
    fun provideOpenWeatherAppIdInterceptor(): Interceptor = object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            val originalHttpUrl = original.url
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter(APP_ID_KEY, APP_ID_VALUE)
                .build()
            val requestBuilder = original.newBuilder()
                .url(url)
            val request = requestBuilder.build()
            return chain.proceed(request)
        }
    }

    @Singleton
    @Provides
    fun provideHttpClient(appId: Interceptor, logger: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(appId)
            .addInterceptor(logger)
            .build()

    @Singleton
    @Provides
    fun provideWeatherService(httpClient: OkHttpClient): WeatherService =
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(httpClient)
            .build()
            .create(WeatherService::class.java)
}
