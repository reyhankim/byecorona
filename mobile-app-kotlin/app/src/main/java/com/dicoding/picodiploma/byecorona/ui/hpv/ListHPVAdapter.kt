package com.dicoding.picodiploma.byecorona.ui.hpv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.byecorona.databinding.ItemHpvBinding

class ListHPVAdapter : RecyclerView.Adapter<ListHPVAdapter.ListHPVViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHPVViewHolder {
        val itemHpvBinding = ItemHpvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListHPVViewHolder(itemHpvBinding)
    }

    override fun onBindViewHolder(holder: ListHPVViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class ListHPVViewHolder(private val binding: ItemHpvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {}
    }
}