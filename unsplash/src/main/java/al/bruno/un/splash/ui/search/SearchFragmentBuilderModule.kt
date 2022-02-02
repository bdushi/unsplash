package al.bruno.un.splash.ui.search

import al.bruno.un.splash.ui.search.collection.SearchCollectionFragment
import al.bruno.un.splash.ui.search.photo.SearchPhotoFragment
import al.bruno.un.splash.ui.search.user.SearchUserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchFragmentBuilderModule {
    @ContributesAndroidInjector
    internal abstract fun contributeSearchPhotoFragment(): SearchPhotoFragment
    @ContributesAndroidInjector
    internal abstract fun contributeSearchCollectionFragment(): SearchCollectionFragment
    @ContributesAndroidInjector
    internal abstract fun contributeSearchUserFragment(): SearchUserFragment
}