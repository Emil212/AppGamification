package com.example.menuprueba.ui

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.menuprueba.R
import com.example.menuprueba.databinding.ActivityMainBinding
import com.example.menuprueba.ui.rutinas.IOnBackPressed
import com.google.android.material.navigation.NavigationView



class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding


    override fun onBackPressed() {
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main)
        navHost?.let { navFragment ->
            navFragment.childFragmentManager.primaryNavigationFragment?.let { fragment ->
                (fragment as? IOnBackPressed)?.onBackPressed()?.not()?.let { isCanceled: Boolean ->
                    if (!isCanceled) {
                        super.onBackPressed()
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(findViewById(R.id.toolbar))
        //setSupportActionBar(binding.appBarMain.toolbar) Marca un error

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_logros,
                R.id.loginFragment,
                R.id.nav_listaEjerciciosFragment,
                R.id.nav_ayuda,
                R.id.nav_logout
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, argument ->
            when (destination.id) {
                R.id.loginFragment -> {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
                R.id.registrationFragment -> {
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }

                R.id.videosFragment -> { //Cuando se elimina, el menú queda sobre el fragment evitando la navegación
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
                R.id.nav_listaEjerciciosFragment -> { //Cuando se elimina, el menú queda sobre el fragment evitando la navegación
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
                R.id.congratulationsFragment -> { //Cuando se elimina, el menú queda sobre el fragment evitando la navegación
                    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
                else -> {
                    //binding.navView.visibility = View.VISIBLE
                    //drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}