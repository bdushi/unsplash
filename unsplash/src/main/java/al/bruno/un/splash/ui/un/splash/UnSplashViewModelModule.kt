package al.bruno.un.splash.ui.un.splash

import al.bruno.di.ViewModelKey
import al.bruno.di.factory.ViewModelProviderFactory
import al.bruno.un.splash.ui.un.splash.home.UnSplashHomeViewModel
import al.bruno.un.splash.ui.search.UnSplashSearchViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class UnSplashViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UnSplashHomeViewModel::class)
    abstract fun bindUnSplashHomeViewModel(unSplashHomeViewModel: UnSplashHomeViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}