package com.dicoding.picodiploma.byecorona.ui.cctv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.byecorona.data.model.CCTV
import com.dicoding.picodiploma.byecorona.data.model.Cluster
import com.dicoding.picodiploma.byecorona.databinding.ActivityListCctvBinding
import com.dicoding.picodiploma.byecorona.ui.home.DetailCCTVFragment

class ListCCTVActivity : AppCompatActivity() {

    private lateinit var listCctvBinding: ActivityListCctvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listCctvBinding = ActivityListCctvBinding.inflate(layoutInflater)
        setContentView(listCctvBinding.root)

        val adapter = ListCCTVAdapter()

        val data = intent.getBundleExtra(DATA)

        if (data != null) {
            val cctv = data.getParcelable<Cluster>(DetailCCTVFragment.ID_CLUSTER)?.cctvList
            Log.d("haha", cctv?.size.toString())
            adapter.setCCTV(cctv as List<CCTV>)
        }

        listCctvBinding.icBack.setOnClickListener {
            onBackPressed()
        }

        with(listCctvBinding.rvCctv) {
            layoutManager = LinearLayoutManager(this@ListCCTVActivity)
            setHasFixedSize(true)
            this.adapter = adapter
        }
    }

    companion object {
        const val DATA = "data"
    }
}