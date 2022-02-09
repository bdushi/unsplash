package al.bruno.un.splash.di

import al.bruno.un.splash.data.source.UnSplashCollectionDataSource
import al.bruno.un.splash.data.source.UnSplashPhotoDataSource
import al.bruno.un.splash.data.source.UnSplashSearchDataSource
import al.bruno.un.splash.data.source.UnSplashUserDataSource
import al.bruno.un.splash.data.source.remote.UnSplashCollectionRemoteDataSource
import al.bruno.un.splash.data.source.remote.UnSplashPhotoRemoteDataSource
import al.bruno.un.splash.data.source.remote.UnSplashSearchRemoteDataSource
import al.bruno.un.splash.data.source.remote.UnSplashUserRemoteDataSource
import al.bruno.un.splash.data.source.remote.service.UnSplashCollectionService
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class UnSplashDataSourceModule {
    @Singleton
    @Binds
    abstract fun provideUnSplashRemoteDataSource(dataSource: UnSplashSearchRemoteDataSource): UnSplashSearchDataSource

    @Singleton
    @Binds
    abstract fun provideUnSplashPhotoRemoteDataSource(dataSource: UnSplashPhotoRemoteDataSource): UnSplashPhotoDataSource

    @Singleton
    @Binds
    abstract fun provideUnSplashCollectionRemoteDataSource(dataSource: UnSplashCollectionRemoteDataSource): UnSplashCollectionDataSource

    @Singleton
    @Binds
    abstract fun provideUnSplashUserRemoteDataSource(dataSource: UnSplashUserRemoteDataSource): UnSplashUserDataSource

}