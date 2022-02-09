package al.bruno.un.splash.di

import al.bruno.un.splash.ui.UnSplashActivity
import al.bruno.un.splash.ui.search.SearchFragmentBuilderModule
import al.bruno.un.splash.ui.search.UnSplashSearchActivity
import al.bruno.un.splash.ui.search.UnSplashSearchViewModelModule
import al.bruno.un.splash.ui.un.splash.UnSplashFragmentBuilderModule
import al.bruno.un.splash.ui.un.splash.UnSplashViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UnSplashActivityBindingModule {
    @ContributesAndroidInjector(modules = [UnSplashFragmentBuilderModule::class, UnSplashViewModelModule::class])
    abstract fun unSplashActivity(): UnSplashActivity

    @ContributesAndroidInjector(modules = [SearchFragmentBuilderModule::class, UnSplashSearchViewModelModule::class])
    abstract fun unSplashSearchActivity(): UnSplashSearchActivity
}