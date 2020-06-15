package com.manuflowers.businesscard

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val quotesList by lazy {
        listOf(
            R.string.first_quote,
            R.string.second_quote,
            R.string.third_quote,
            R.string.fourth_quote,
            R.string.fifth_quote,
            R.string.sixth_quote,
            R.string.seventh_quote,
            R.string.eighth_quote,
            R.string.ninth_quote
        )
    }

    private val fontsList by lazy {
        listOf(
            R.font.atomic_age,
            R.font.berkshire_swash,
            R.font.cherry_cream_soda,
            R.font.delius_swash_caps,
            R.font.atomic_age,
            R.font.berkshire_swash,
            R.font.cherry_cream_soda,
            R.font.delius_swash_caps,
            R.font.delius_swash_caps
        )
    }

    private var counter = 0

    companion object {
        const val COUNTER_KEY = "COUNTER_KEY"
        const val SETTINGS_FRAGMENT = "SettingsFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mainBottomAppBar)
        setupListeners()
        verifyBundle(savedInstanceState)
    }

    /*
    * Save counter value to survive configuration changes
    * */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(COUNTER_KEY, counter)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        when (item.itemId) {
            R.id.info -> showInfo()
            R.id.share -> shareData()
            R.id.settings -> {
                SettingsFragment().show(supportFragmentManager, SETTINGS_FRAGMENT)
            }
        }
        return true
    }

    /*
    * Method in charge verify if exist some data in bundle
    * If exists update counter and bind views
    * else just bind views
    * */
    private fun verifyBundle(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt(COUNTER_KEY)
            bindViews()
        } else {
            bindViews()
        }
    }

    private fun setupListeners() {
        reloadFloatingActionButton?.setOnClickListener {
            changeIndexOfQuote()
            bindViews()
        }
    }

    private fun bindViews() {
        quoteTextView?.text = getString(quotesList[counter])
        val typeFace = ResourcesCompat.getFont(this, fontsList[counter])
        quoteTextView.typeface = typeFace
    }

    /**
     * Method in charge to show an alert dialog with app information
     * */
    private fun showInfo() {
        val dialogTitle = getString(R.string.about_title)
        val dialogMessage = getString(R.string.about_message, BuildConfig.VERSION_NAME)

        val builder = AlertDialog.Builder(this)
        builder.setTitle(dialogTitle)
        builder.setMessage(dialogMessage)
        builder.create().show()
    }

    /**
     * Method responsible for increasing counter by one
     * */
    private fun changeIndexOfQuote() {
        counter += 1
        if (counter == quotesList.size) {
            counter = 0
        }
    }

    /*
    * Share data between applications with an explicit intent
    * */
    private fun shareData() {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(
                Intent.EXTRA_TEXT,
                getString(R.string.user_data)
            )
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}