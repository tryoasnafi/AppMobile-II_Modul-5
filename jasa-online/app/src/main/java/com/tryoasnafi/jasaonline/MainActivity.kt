package com.tryoasnafi.jasaonline

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var homeFragment: HomeFragment
    private lateinit var serviceFragment: ServiceFragment
    private lateinit var profileFragment: ProfileFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener {
            openFragment(it.itemId)
        }

        openFragment(R.id.nav_home)
    }



    private fun openFragment(fragmentId: Int): Boolean {
        when (fragmentId) {
            R.id.nav_home -> {
                homeFragment = HomeFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, homeFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.nav_service -> {
                serviceFragment = ServiceFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, serviceFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.nav_profile -> {
                profileFragment = ProfileFragment()
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, profileFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .commit()
            }
            R.id.nav_logout -> {
                val builder = AlertDialog.Builder(this)
                builder
                    .setTitle("Keluar Akun")
                    .setMessage("Apakah anda yakin keluar dari akun saat ini?")
                    .setIcon(R.drawable.ic_exit_24)
                    .setPositiveButton("Ya") { dialog, _ ->
                        dialog.dismiss()
                        Snackbar
                            .make(drawerLayout, "Anda klik ya!", Snackbar.LENGTH_LONG)
                            .show()
                    }.setNegativeButton("Tidak") { dialog, _ ->
                        dialog.dismiss()
                        Snackbar
                            .make(drawerLayout, "Anda klik tidak!", Snackbar.LENGTH_LONG)
                            .show()
                    }

                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}