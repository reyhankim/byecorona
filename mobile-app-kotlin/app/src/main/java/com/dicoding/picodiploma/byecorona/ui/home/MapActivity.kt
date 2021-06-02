package com.dicoding.picodiploma.byecorona.ui.home

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.byecorona.R
import com.dicoding.picodiploma.byecorona.data.model.Cluster
import com.dicoding.picodiploma.byecorona.databinding.ActivityMapBinding
import com.dicoding.picodiploma.byecorona.databinding.NavHeaderMainBinding
import com.dicoding.picodiploma.byecorona.ui.home.DetailCCTVFragment.Companion.ID_CLUSTER
import com.dicoding.picodiploma.byecorona.ui.notification.NotificationActivity
import com.dicoding.picodiploma.byecorona.viewmodel.ViewModelFactory
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import com.mancj.materialsearchbar.MaterialSearchBar

class MapActivity : AppCompatActivity(), OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener, MaterialSearchBar.OnSearchActionListener,
    GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var activityMapBinding: ActivityMapBinding
    private lateinit var navHeaderMainBinding: NavHeaderMainBinding
    private lateinit var viewModel: MapViewModel
    private lateinit var markerMap: HashMap<Marker, Cluster>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMapBinding = ActivityMapBinding.inflate(layoutInflater)
        navHeaderMainBinding = NavHeaderMainBinding.bind(activityMapBinding.navView.getHeaderView(0))
        setContentView(activityMapBinding.root)

        markerMap = HashMap()

        FirebaseMessaging.getInstance().subscribeToTopic("news")

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[MapViewModel::class.java]

        activityMapBinding.navView.setNavigationItemSelectedListener(this)
        activityMapBinding.appBarMain.searchBar.setOnSearchActionListener(this)

        populateUser()

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        activityMapBinding.appBarMain.icNotification.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            startActivity(intent)
        }

        activityMapBinding.btnLogout.setOnClickListener {
            Firebase.auth.signOut()
            finish()
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setMapStyle(
            MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json)
        )

        val jakarta = LatLng(-6.200000, 106.816666)

        viewModel.getListCluster().observe(this, { cluster ->
            for (clt in cluster) {
                addMarker(clt)
            }
        })

        mMap

        mMap.moveCamera(CameraUpdateFactory.newLatLng(jakarta))

        mMap.setOnMarkerClickListener(this)

    }

    private fun addMarker(cluster: Cluster) {
        val clusterMarker = mMap.addMarker(MarkerOptions().position(LatLng(cluster.clusterLatitude!!, cluster.clusterLongitude!!)).title(cluster.clusterName))
        clusterMarker.setIcon(bitmapFromVector(applicationContext, R.drawable.ic_videocam_blue_24px))

        markerMap[clusterMarker] = cluster
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }

    override fun onSearchStateChanged(enabled: Boolean) {

    }

    override fun onSearchConfirmed(text: CharSequence?) {

    }

    override fun onButtonClicked(buttonCode: Int) {
        when (buttonCode) {

            MaterialSearchBar.BUTTON_NAVIGATION -> {
                activityMapBinding.drawerLayout.openDrawer(GravityCompat.START)
            }
        }
    }

    private fun bitmapFromVector(context: Context, vectorResId: Int): BitmapDescriptor {
        val vectorDrawable = ContextCompat.getDrawable(context, vectorResId)

        vectorDrawable!!.setBounds(0, 0, vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight)

        val bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)

        vectorDrawable.draw(canvas)

        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }

    private fun populateUser() {
        Glide.with(this)
            .load(Firebase.auth.currentUser?.photoUrl)
            .into(navHeaderMainBinding.imgProfile)

        navHeaderMainBinding.tvEmail.text = Firebase.auth.currentUser?.email
        navHeaderMainBinding.tvName.text = Firebase.auth.currentUser?.displayName
    }

    override fun onMarkerClick(p0: Marker): Boolean {
        val clt = markerMap[p0]
        val sheet = DetailCCTVFragment()
        val bundle = Bundle()
        bundle.putParcelable(ID_CLUSTER, clt)
        sheet.arguments = bundle
        sheet.show(supportFragmentManager, "DetailCCTVFragment")
        return false
    }
}