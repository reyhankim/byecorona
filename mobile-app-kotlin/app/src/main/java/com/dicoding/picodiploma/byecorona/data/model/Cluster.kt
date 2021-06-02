package com.dicoding.picodiploma.byecorona.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Cluster(
    val clusterId: Int? = null,
    val clusterName: String? = null,
    val cctvList: List<CCTV?>? = null,
    val clusterLongitude: Double? = null,
    val clusterLatitude: Double? = null
) : Parcelable
