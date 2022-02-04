package al.bruno.un.splash.ui.un.splash

import al.bruno.un.splash.ui.un.splash.collections.CollectionsFragment
import al.bruno.un.splash.ui.un.splash.photo.PhotoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UnSplashFragmentBuilderModule {
    @ContributesAndroidInjector
    internal abstract fun contributeHomeFragment(): PhotoFragment
    @ContributesAndroidInjector
    internal abstract fun contributeCollectionsFragment(): CollectionsFragment
}