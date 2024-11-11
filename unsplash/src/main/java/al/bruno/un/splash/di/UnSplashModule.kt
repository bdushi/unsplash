package al.bruno.un.splash.di

import al.bruno.un.splash.data.source.remote.service.UnSplashCollectionService
import al.bruno.un.splash.data.source.remote.service.UnSplashPhotoService
import al.bruno.un.splash.data.source.remote.service.UnSplashSearchService
import al.bruno.un.splash.data.source.remote.service.UnSplashUserService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
class UnSplashModule {
    @Provides
    @Singleton
    fun providerRetrofit(

    ): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return Retrofit
            .Builder()
            .baseUrl("https://api.unsplash.com/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(httpLoggingInterceptor.apply { httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY })
                    .addInterceptor(fun(chain: Interceptor.Chain): Response {
                        return chain.proceed(
                            chain.request()
                                .newBuilder()
                                .addHeader(
                                    "Authorization",
                                    "Client-ID AHV-LsEDHkDfDQqA8__oTuHPto3coSeP-GGnJVBAMxA"
                                )
                                .build()
                        )
                    }).build()
            )
            .addConverterFactory(
                Json
                    .asConverterFactory("application/json".toMediaType())
            )
            .build()
    }

    @Provides
    @Reusable
    fun searchUnSplashSearchService(retrofit: Retrofit): UnSplashSearchService {
        return retrofit.create(UnSplashSearchService::class.java)
    }

    @Provides
    @Reusable
    fun searchUnSplashPhotoService(retrofit: Retrofit): UnSplashPhotoService {
        return retrofit.create(UnSplashPhotoService::class.java)
    }

    @Provides
    @Reusable
    fun searchUnSplashCollectionService(retrofit: Retrofit): UnSplashCollectionService {
        return retrofit.create(UnSplashCollectionService::class.java)
    }

    @Provides
    @Reusable
    fun searchUnSplashUserService(retrofit: Retrofit): UnSplashUserService{
        return retrofit.create(UnSplashUserService::class.java)
    }
}