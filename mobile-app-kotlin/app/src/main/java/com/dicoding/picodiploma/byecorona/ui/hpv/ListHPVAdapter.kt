package com.dicoding.picodiploma.byecorona.ui.hpv

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.byecorona.R
import com.dicoding.picodiploma.byecorona.data.model.Violation
import com.dicoding.picodiploma.byecorona.databinding.ItemHpvBinding
import com.dicoding.picodiploma.byecorona.ui.hpv.DetailHPVActivity.Companion.DATA
import com.google.firebase.storage.FirebaseStorage

class ListHPVAdapter : RecyclerView.Adapter<ListHPVAdapter.ListHPVViewHolder>() {

    private var listViolations = ArrayList<Violation>()

    fun setViolations(violations: List<Violation>) {
        if (violations == null) return
        this.listViolations.clear()
        this.listViolations.addAll(violations)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHPVViewHolder {
        val itemHpvBinding = ItemHpvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListHPVViewHolder(itemHpvBinding)
    }

    override fun onBindViewHolder(holder: ListHPVViewHolder, position: Int) {
        val violation = listViolations[position]
        holder.bind(violation)
    }

    override fun getItemCount(): Int {
        return listViolations.size
    }

    inner class ListHPVViewHolder(private val binding: ItemHpvBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(violation: Violation) {
            with(binding) {
                tvDatetime.text = violation.violationDate
                tvViolationName.text = violation.violationName
                tvActionStatus.text = if (violation.action != null) violation.action.actionRemark else "Belum ditangani"
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailHPVActivity::class.java)
                    intent.putExtra(DATA, violation)
                    itemView.context.startActivity(intent)
                }
                val storage = FirebaseStorage.getInstance()
                val imgRef = storage.reference.child("test/ByeCorona1.jpeg")
                imgRef.downloadUrl.addOnSuccessListener {
                    val imgURL = it.toString()

                    Glide.with(itemView.context)
                        .load(imgURL)
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                        .error(R.drawable.ic_error)
                        .into(imgThumbnail)
                }
            }
        }
    }
}