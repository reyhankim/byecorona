package com.dicoding.picodiploma.byecorona.ui.hpv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.byecorona.databinding.ActivityListHpvBinding
import com.dicoding.picodiploma.byecorona.utils.DataDummy

class ListHPVActivity : AppCompatActivity() {
    private lateinit var listHpvBinding: ActivityListHpvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listHpvBinding = ActivityListHpvBinding.inflate(layoutInflater)
        setContentView(listHpvBinding.root)

        val adapter = ListHPVAdapter()

        val id = intent.getIntExtra(ID_CCTV, 0)
        if (id != 0) {
            adapter.setViolations(DataDummy.generateDummyViolations())
            adapter.notifyDataSetChanged()
        }

        with(listHpvBinding.rvHpv) {
            layoutManager = LinearLayoutManager(this@ListHPVActivity)
            setHasFixedSize(true)
            this.adapter = adapter
        }

        listHpvBinding.icBack.setOnClickListener {
            onBackPressed()
        }
    }

    companion object {
        const val ID_CCTV = "id_cctv"
    }
}