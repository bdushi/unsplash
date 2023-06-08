package al.bruno.un.splash.ui

import al.bruno.di.base.BaseActivity
import al.bruno.un.splash.databinding.ActivityUnSplashBinding
import al.bruno.un.splash.ui.un.splash.UnSplashFragment
import android.os.Bundle
import dagger.android.AndroidInjector

class UnSplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activitySearchBinding = ActivityUnSplashBinding.inflate(layoutInflater)
        setContentView(activitySearchBinding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(activitySearchBinding.unSplashRoot.id, UnSplashFragment(), "TAG")
            .commit()
    }
    override fun androidInjector(): AndroidInjector<Any> {
        return fragmentInjector
    }
}