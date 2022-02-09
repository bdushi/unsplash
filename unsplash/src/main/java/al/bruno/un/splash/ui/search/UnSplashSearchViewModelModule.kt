package al.bruno.un.splash.ui.search

import al.bruno.di.ViewModelKey
import al.bruno.di.factory.ViewModelProviderFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class UnSplashSearchViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UnSplashSearchViewModel::class)
    abstract fun bindUnSplashSearchViewModel(unSplashSearchViewModel: UnSplashSearchViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory
}