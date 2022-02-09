package al.bruno.unsplash.di

import al.bruno.unsplash.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppBindingBindingModule {
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}