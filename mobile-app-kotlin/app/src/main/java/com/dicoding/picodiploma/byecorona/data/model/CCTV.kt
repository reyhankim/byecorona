package com.dicoding.picodiploma.byecorona.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CCTV(
    val cctvId: Int? = null,
    val cctvName: String? = null,
    val cctvLongitude: Double? = null,
    val cctvLatitude: Double? = null
) : Parcelable
