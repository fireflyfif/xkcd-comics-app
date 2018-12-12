package com.example.ivaivanova.myxkcd.ui

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.ivaivanova.myxkcd.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val onNavigationListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.navigation_comics -> {
                toolbar.title = "Comics"
                val comicsFragment = ComicsFragment.newInstance()
                openFragment(comicsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_search -> {
                toolbar.title = "Search"
                val comicsFragment = ComicsFragment.newInstance()
                openFragment(comicsFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_favorites -> {
                toolbar.title = "Favs"
                val comicsFragment = ComicsFragment.newInstance()
                openFragment(comicsFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        // Open the first fragment when launching.
        // TODO: Set a default fragment to be shown when launching!
        openFragment(ComicsFragment.newInstance())

        initBottomNavigation()
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun initBottomNavigation() {
        bottom_navigation.display
        bottom_navigation.setOnNavigationItemSelectedListener(onNavigationListener)
    }

   private fun openFragment(fragment: Fragment) {
       val transaction = supportFragmentManager.beginTransaction()
       transaction.replace(R.id.content_container, fragment)
       transaction.addToBackStack(null)
       transaction.commit()
   }
}
