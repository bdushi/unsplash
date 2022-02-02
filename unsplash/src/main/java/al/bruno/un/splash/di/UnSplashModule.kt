package al.bruno.un.splash.di

import al.bruno.un.splash.data.source.remote.service.UnSplashSearchService
import al.bruno.un.splash.utils.MyRxBus
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class UnSplashModule {
    @Provides
    @Singleton
    fun providesMyRxBus(): MyRxBus {
        return MyRxBus()
    }
    @Provides
    @Singleton
    fun providerRetrofit(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return Retrofit
            .Builder()
            .baseUrl("https://api.unsplash.com/")
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply { httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS })
                    .addInterceptor(fun(chain: Interceptor.Chain): Response {
                        return chain.proceed(
                            chain.request()
                                .newBuilder()
                                .addHeader(
                                    "Authorization",
                                    "Client-ID 6ba7be9129630ab29d203a38b66cad0c4aa04824372fc92c849ec486f4ed70e9"
                                )
                                .build()
                        )
                    }).build()
            ).addConverterFactory(
                GsonConverterFactory
                .create(
                    GsonBuilder()
                        .setDateFormat("yyyy/MM/dd")
                        .create()))
            .build()
    }

    @Provides
    @Reusable
    fun searchService(retrofit: Retrofit): UnSplashSearchService {
        return retrofit.create(UnSplashSearchService::class.java)
    }
}