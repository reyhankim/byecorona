package com.dicoding.picodiploma.byecorona.ui.cctv

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.byecorona.data.model.CCTV
import com.dicoding.picodiploma.byecorona.data.model.Cluster
import com.dicoding.picodiploma.byecorona.databinding.ItemCctvBinding

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
                cctvName.text = "haha"
                Log.d("haha", "bind: ")
            }
        }

    }
}