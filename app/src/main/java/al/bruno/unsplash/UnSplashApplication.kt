package al.bruno.unsplash

import al.bruno.unsplash.di.DaggerAppComponent
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class UnSplashApplication : Application(), HasAndroidInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>
    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }
    override fun androidInjector(): AndroidInjector<Any> {
        return activityInjector
    }

}