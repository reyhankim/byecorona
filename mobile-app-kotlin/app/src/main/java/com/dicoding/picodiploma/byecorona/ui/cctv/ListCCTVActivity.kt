package com.dicoding.picodiploma.byecorona.ui.cctv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.byecorona.data.model.CCTV
import com.dicoding.picodiploma.byecorona.data.model.Cluster
import com.dicoding.picodiploma.byecorona.databinding.ActivityListCctvBinding
import com.dicoding.picodiploma.byecorona.ui.home.DetailCCTVFragment
import com.dicoding.picodiploma.byecorona.viewmodel.ViewModelFactory

class ListCCTVActivity : AppCompatActivity() {

    private lateinit var listCctvBinding: ActivityListCctvBinding
    private lateinit var viewModel: CCTVViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listCctvBinding = ActivityListCctvBinding.inflate(layoutInflater)
        setContentView(listCctvBinding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[CCTVViewModel::class.java]

        val adapter = ListCCTVAdapter()

        val data = intent.getBundleExtra(DATA)

        if (data != null) {
            val cctv = data.getParcelable<Cluster>(DetailCCTVFragment.DATA_CLUSTER)?.cctvList

            showLoading(true)
            viewModel.getListCCTV(cctv as List<CCTV>).observe(this, { cctv ->
                showLoading(false)
                Log.d("hoho1", cctv.toString())
                adapter.setCCTV(cctv)
                adapter.notifyDataSetChanged()
            })

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

    private fun showLoading(state: Boolean) {
        if (state) {
            listCctvBinding.progressBar.visibility = View.VISIBLE
        } else {
            listCctvBinding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        const val DATA = "data"
    }
}