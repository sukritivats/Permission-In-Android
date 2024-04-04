package com.example.cofferassignment

import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.cofferassignment.databinding.ActivityMainBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        setupNavigation()
        requestLocationPermission()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        setupDrawer()
        setupClickListeners()
        setupDestinationChangeListener()
    }

    private fun setupDrawer() {
        val toggle = ActionBarDrawerToggle(
            this,
            binding?.drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        binding?.drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()
        binding?.ivDrawer?.setOnClickListener {
            if (binding?.navView?.let { navView -> binding?.drawerLayout?.isDrawerOpen(navView) } == true) {
                binding?.drawerLayout?.closeDrawer(binding?.navView!!)
            } else {
                binding?.navView?.let { it1 -> binding?.drawerLayout?.openDrawer(it1) }
            }
        }
    }

    private fun setupClickListeners() {
        binding?.llExplore?.setOnClickListener {
            navController.navigate(R.id.fragmentExplore)
        }

        binding?.ivRefine?.setOnClickListener {
            navController.navigate(R.id.fragmentRefine)
            hideActionBarAndBottomNavigation()
        }
    }

    private fun setupDestinationChangeListener() {
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (destination.id == R.id.navExplore) {
                showActionBarAndBottomNavigation()
            }
        }
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestAndDisplayLocation()
            } else {
                // Handle permission denied
            }
        }
    }

    private fun requestAndDisplayLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    location?.let {
                        val geocoder = Geocoder(this, Locale.getDefault())
                        val addresses: List<Address>? = geocoder.getFromLocation(
                            location.latitude,
                            location.longitude,
                            1
                        )
                        if (addresses != null && addresses.isNotEmpty()) {
                            val address: Address = addresses[0]
                            val city = address.locality ?: "Unknown"

                            binding?.tvUserLocation?.text = city
                        } else {
                            binding?.tvUserLocation?.text = "Address not found"
                        }
                    }
                }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    fun showActionBarAndBottomNavigation() {
        binding?.clActionBar?.visibility = View.VISIBLE
        binding?.btmNavigation?.visibility = View.VISIBLE
    }

    fun hideActionBarAndBottomNavigation() {
        binding?.clActionBar?.visibility = View.GONE
        binding?.btmNavigation?.visibility = View.GONE
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 100
    }
}
