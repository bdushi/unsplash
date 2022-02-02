package al.bruno.un.splash.di

import al.bruno.un.splash.data.source.UnSplashSearchDataSource
import al.bruno.un.splash.data.source.remote.UnSplashSearchRemoteDataSource
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class UnSplashDataSourceModule {
    @Singleton
    @Binds
    abstract fun provideUnSplashRemoteDataSource(dataSource: UnSplashSearchRemoteDataSource): UnSplashSearchDataSource

}