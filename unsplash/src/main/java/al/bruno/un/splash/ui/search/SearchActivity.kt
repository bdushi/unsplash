package al.bruno.un.splash.ui.search

import al.bruno.adapter.OnClickListener
import al.bruno.di.base.BaseActivity
import al.bruno.un.splash.R
import al.bruno.un.splash.model.api.Photo
import al.bruno.un.splash.model.api.Search
import al.bruno.un.splash.ui.search.collection.SearchCollectionFragment
import al.bruno.un.splash.ui.search.photo.SearchPhotoFragment
import al.bruno.un.splash.ui.search.user.SearchUserFragment
import al.bruno.un.splash.utils.MyRxBus
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.textfield.TextInputEditText
import dagger.android.AndroidInjection
import javax.inject.Inject

class SearchActivity : BaseActivity() {
    @Inject
    lateinit var myRxBusSearch: MyRxBus

    val search = Search(null, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_search)
        val viewPager: ViewPager2 = findViewById(R.id.un_splash_view_pager)
        val tabLayout: TabLayout = findViewById(R.id.un_splash_tab_layout)
        val textInput: TextInputEditText = findViewById(R.id.un_splash_text_input)
        val searchOptionsSpinner: Spinner = findViewById(R.id.search_options_spinner)
        setSupportActionBar(findViewById(R.id.un_splash_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 3
            }

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> SearchPhotoFragment().setOnClickListener(object : OnClickListener<Photo> {
                        override fun onClick(t: Photo) {
                            setResult(Activity.RESULT_OK, Intent().putExtra("PHOTO", t.urls.regular))
                            finish()
                        }
                    })
                    1 -> SearchCollectionFragment()
                    2 -> SearchUserFragment()
                    else -> SearchPhotoFragment()
                }
            }
        }
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = resources.getStringArray(R.array.un_splash_tabs)[position]
        }.attach()

        viewPager.offscreenPageLimit = 2
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            // TODO
        })

        textInput.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(textView: TextView, i: Int, keyEvent: KeyEvent): Boolean {
                if (keyEvent.action == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    search.query = textView.text
                    myRxBusSearch.setRxBus(search)
                    return true
                }
                return false
            }
        })

        textInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                search.query = p0
                myRxBusSearch.setRxBus(search)
            }

        })
        textInput.isFocusable = true
        textInput.requestFocus()


        if (textInput.requestFocus() && textInput.text.toString() == "") {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        }
        searchOptionsSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                search.orientation = p0?.selectedItem.toString()
            }

        }
    }

    override fun onResume() {
        super.onResume()
        /*if (mEditText.getText().toString() != "") {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        }*/
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}