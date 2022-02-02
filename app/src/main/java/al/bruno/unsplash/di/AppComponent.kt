package al.bruno.unsplash.di

import al.bruno.un.splash.di.UnSplashActivityBindingModule
import al.bruno.un.splash.di.UnSplashDataSourceModule
import al.bruno.un.splash.di.UnSplashModule
import al.bruno.unsplash.UnSplashApplication
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    UnSplashModule::class,
    UnSplashDataSourceModule::class,
    UnSplashActivityBindingModule::class,
    AppBindingBindingModule::class])
interface AppComponent : AndroidInjector<UnSplashApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
    override fun inject(app: UnSplashApplication)
}