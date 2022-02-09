package al.bruno.un.splash.ui.un.splash

import al.bruno.un.splash.ui.search.SearchFragmentBuilderModule
import al.bruno.un.splash.ui.un.splash.collections.CollectionsFragment
import al.bruno.un.splash.ui.un.splash.photo.PhotoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UnSplashFragmentBuilderModule : SearchFragmentBuilderModule() {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): PhotoFragment
    @ContributesAndroidInjector
    abstract fun contributeCollectionsFragment(): CollectionsFragment
    @ContributesAndroidInjector
    abstract fun contributeUnSplashFragment(): UnSplashFragment
}