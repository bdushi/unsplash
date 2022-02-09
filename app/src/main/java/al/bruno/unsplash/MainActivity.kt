package al.bruno.unsplash

import PHOTO
import al.bruno.di.base.BaseActivity
import al.bruno.un.splash.ui.search.UnSplashSearchActivity
import al.bruno.un.splash.ui.UnSplashActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import al.bruno.unsplash.databinding.ActivityMainBinding
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.core.view.WindowCompat

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    private val activityResult =
        registerForActivityResult(object : ActivityResultContract<Intent, String?>() {
            override fun createIntent(context: Context, intent: Intent): Intent {
                return intent
            }
            override fun parseResult(resultCode: Int, intent: Intent?): String? {
                if (resultCode != Activity.RESULT_OK) {
                    return null
                }
                return intent?.getStringExtra(PHOTO)
            }
        }) {
            it?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.unSplash.setOnClickListener {
            activityResult.launch(Intent(this@MainActivity, UnSplashActivity::class.java))
        }
        binding.unSplashSearch.setOnClickListener {
            activityResult.launch(Intent(this@MainActivity, UnSplashSearchActivity::class.java))
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