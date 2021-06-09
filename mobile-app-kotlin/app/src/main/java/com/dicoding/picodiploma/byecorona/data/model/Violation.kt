package com.dicoding.picodiploma.byecorona.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Violation(
    val violationId: Int? = null,
    val violationName: String? = null,
    val violationType: String? = null,
    val cctvName: String? = null,
    val thumbnailPath: String? = null,
    val isActionTaken: Boolean = false,
    val violationDate: String? = null,
    val action: Action? = null
) : Parcelable
