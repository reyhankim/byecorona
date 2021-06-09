package com.dicoding.picodiploma.byecorona.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Action(
    val actionId: Int? = null,
    val actionType: String? = null,
    val actionDate: String? = null,
    val actionRemark: String? = null,
) : Parcelable
