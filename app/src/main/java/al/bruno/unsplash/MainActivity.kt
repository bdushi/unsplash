package al.bruno.unsplash

import PHOTO
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
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val activityResult =
        registerForActivityResult(object : ActivityResultContract<Intent, String?>() {
            override fun createIntent(context: Context, input: Intent): Intent {
                return input
            }
            override fun parseResult(resultCode: Int, intent: Intent?): String? {
                return if (resultCode == Activity.RESULT_OK) {
                     intent?.getStringExtra(PHOTO)
                } else {
                    null
                }
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
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}