package com.dicoding.picodiploma.byecorona.ui.cctv

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.byecorona.data.model.CCTV
import com.dicoding.picodiploma.byecorona.data.model.Cluster
import com.dicoding.picodiploma.byecorona.databinding.ItemCctvBinding
import com.dicoding.picodiploma.byecorona.ui.hpv.ListHPVActivity
import com.dicoding.picodiploma.byecorona.ui.hpv.ListHPVActivity.Companion.ID_CCTV

class ListCCTVAdapter : RecyclerView.Adapter<ListCCTVAdapter.ListCCTVViewHolder>() {
    private var listCCTV = ArrayList<CCTV>()

    fun setCCTV(cctv: List<CCTV>) {
        if (cctv == null) return
        this.listCCTV.clear()
        this.listCCTV.addAll(cctv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCCTVViewHolder {
        val itemCctvBinding = ItemCctvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListCCTVViewHolder(itemCctvBinding)
    }

    override fun onBindViewHolder(holder: ListCCTVViewHolder, position: Int) {
        val cctv = listCCTV[position]
        holder.bind(cctv)
    }

    override fun getItemCount(): Int {
        return listCCTV.size
    }

    inner class ListCCTVViewHolder(private val binding: ItemCctvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cctv: CCTV) {
            with(binding) {
                cctvName.text = cctv.cctvName
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, ListHPVActivity::class.java)
                    intent.putExtra(ID_CCTV, cctv.cctvId)
                    itemView.context.startActivity(intent)
                }
            }
        }

    }
}