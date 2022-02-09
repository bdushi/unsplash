package al.bruno.un.splash.ui.search

import al.bruno.un.splash.ui.search.collection.SearchCollectionFragment
import al.bruno.un.splash.ui.search.photo.SearchPhotoFragment
import al.bruno.un.splash.ui.search.user.SearchUserFragment
import al.bruno.un.splash.ui.un.splash.UnSplashFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchFragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeSearchPhotoFragment(): SearchPhotoFragment
    @ContributesAndroidInjector
    abstract fun contributeSearchCollectionFragment(): SearchCollectionFragment
    @ContributesAndroidInjector
    abstract fun contributeSearchUserFragment(): SearchUserFragment
    @ContributesAndroidInjector
    abstract fun contributeUnSplashSearchFragment(): UnSplashSearchFragment
}