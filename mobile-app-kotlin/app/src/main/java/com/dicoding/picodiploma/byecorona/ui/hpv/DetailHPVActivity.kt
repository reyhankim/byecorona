package com.dicoding.picodiploma.byecorona.ui.hpv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.byecorona.R
import com.dicoding.picodiploma.byecorona.databinding.ActivityDetailHpvBinding
import com.google.firebase.storage.FirebaseStorage

class DetailHPVActivity : AppCompatActivity() {
    private lateinit var detailHpvBinding: ActivityDetailHpvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailHpvBinding = ActivityDetailHpvBinding.inflate(layoutInflater)
        setContentView(detailHpvBinding.root)

        val storage = FirebaseStorage.getInstance()
        val imgRef = storage.reference.child("test/ByeCorona.png")
        imgRef.downloadUrl.addOnSuccessListener {
            val imgURL = it.toString()

            Glide.with(this)
                .load(imgURL)
                .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                .error(R.drawable.ic_error)
                .into(detailHpvBinding.imgView)
        }
    }
}