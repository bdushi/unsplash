package al.bruno.un.splash.di

import al.bruno.un.splash.data.source.remote.service.UnSplashCollectionService
import al.bruno.un.splash.data.source.remote.service.UnSplashPhotoService
import al.bruno.un.splash.data.source.remote.service.UnSplashSearchService
import al.bruno.un.splash.data.source.remote.service.UnSplashUserService
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
                    .addInterceptor(httpLoggingInterceptor.apply { httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY })
                    .addInterceptor(fun(chain: Interceptor.Chain): Response {
                        return chain.proceed(
                            chain.request()
                                .newBuilder()
                                .addHeader(
                                    "Authorization",
                                    "Client-ID"
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