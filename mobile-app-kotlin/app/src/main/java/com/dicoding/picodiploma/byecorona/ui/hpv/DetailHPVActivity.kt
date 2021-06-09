package com.dicoding.picodiploma.byecorona.ui.hpv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.andreseko.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.byecorona.R
import com.dicoding.picodiploma.byecorona.data.model.Violation
import com.dicoding.picodiploma.byecorona.databinding.ActivityDetailHpvBinding
import com.google.firebase.storage.FirebaseStorage
import java.text.SimpleDateFormat
import java.util.*

class DetailHPVActivity : AppCompatActivity() {
    private lateinit var detailHpvBinding: ActivityDetailHpvBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailHpvBinding = ActivityDetailHpvBinding.inflate(layoutInflater)
        setContentView(detailHpvBinding.root)

        val data = intent.getParcelableExtra<Violation>(DATA)

        if (data != null) {
            detailHpvBinding.tvCctvName.text = data.cctvName
            detailHpvBinding.tvViolationName.text = data.violationName
            detailHpvBinding.tvDatetime.text = data.violationDate
            if (data.isActionTaken) {
                detailHpvBinding.tvStatus.text = data.action!!.actionRemark
                detailHpvBinding.tvLastDatetime.text = data.action!!.actionDate
            } else {
                detailHpvBinding.tvStatus.text = "Belum ditangani"
                detailHpvBinding.tvLastDatetime.text = "-"
            }

            val storage = FirebaseStorage.getInstance()
            val imgRef = storage.reference.child("test/ByeCorona1.jpeg")
            imgRef.downloadUrl.addOnSuccessListener {
                val imgURL = it.toString()

                Glide.with(this)
                    .load(imgURL)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
                    .error(R.drawable.ic_error)
                    .into(detailHpvBinding.imgView)
            }

        }

        detailHpvBinding.icBack.setOnClickListener {
            onBackPressed()
        }

        detailHpvBinding.speaker.setOnClickListener {
            actionConfirm("speaker")
        }

        detailHpvBinding.drone.setOnClickListener {
            actionConfirm("drone")
        }

        detailHpvBinding.watchdog.setOnClickListener {
            actionConfirm("watchdog")
        }
    }

    private fun actionConfirm(action: String) {
        SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
            .setTitleText("Yakin ingin melakukan aksi?")
            .setCancelText("Tidak")
            .setConfirmText("Ya")
            .showCancelButton(true)
            .setConfirmClickListener {
                it.setTitleText("Memproses")
                    .showCancelButton(false)
                    .changeAlertType(SweetAlertDialog.PROGRESS_TYPE)
                Handler(Looper.getMainLooper()).postDelayed({
                    it.setTitleText("Aksi berhasil dilakukan")
                        .setConfirmText("OK")
                        .setCancelClickListener(null)
                        .setConfirmClickListener(null)
                        .changeAlertType(SweetAlertDialog.SUCCESS_TYPE)
                    val date = Date()
                    val format = SimpleDateFormat("yyyy-MM-dd hh:mm")
                    val dateToStr = format.format(date)
                    detailHpvBinding.tvLastDatetime.text = dateToStr
                    detailHpvBinding.tvStatus.text = "Sudah ditangani dengan peringatan melalui $action"
                }, 3000)
            }
            .show()
        detailHpvBinding.menu.close(true)
    }

    companion object {
        const val DATA = "data"
    }
}