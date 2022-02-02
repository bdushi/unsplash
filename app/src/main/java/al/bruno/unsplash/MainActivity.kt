package al.bruno.unsplash

import al.bruno.di.base.BaseActivity
import al.bruno.un.splash.ui.un.splash.UnSplashActivity
import android.os.Bundle
import androidx.core.view.WindowCompat
import android.view.Menu
import android.view.MenuItem
import al.bruno.unsplash.databinding.ActivityMainBinding
import android.content.Intent

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.unSplash.setOnClickListener {
            startActivity(Intent(this@MainActivity, UnSplashActivity::class.java))
        }
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}