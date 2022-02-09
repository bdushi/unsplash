package al.bruno.un.splash.ui.un.splash

import al.bruno.di.ViewModelKey
import al.bruno.di.factory.ViewModelProviderFactory
import al.bruno.un.splash.ui.search.UnSplashSearchViewModel
import al.bruno.un.splash.ui.un.splash.photo.UnSplashPhotoViewModel
import al.bruno.un.splash.ui.un.splash.collections.UnSplashCollectionsViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class UnSplashViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UnSplashPhotoViewModel::class)
    abstract fun bindUnSplashHomeViewModel(unSplashPhotoViewModel: UnSplashPhotoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UnSplashSearchViewModel::class)
    abstract fun bindUnSplashSearchViewModel(unSplashPhotoViewModel: UnSplashSearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UnSplashCollectionsViewModel::class)
    abstract fun bindUnSplashCollectionsViewModel(unSplashCollectionsViewModel: UnSplashCollectionsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}