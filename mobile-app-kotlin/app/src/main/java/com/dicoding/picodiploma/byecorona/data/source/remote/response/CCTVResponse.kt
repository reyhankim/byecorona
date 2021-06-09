package com.dicoding.picodiploma.byecorona.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CCTVResponse(

	@field:SerializedName("cctvLatitude")
	val cctvLatitude: Double? = null,

	@field:SerializedName("cctvId")
	val cctvId: Int? = null,

	@field:SerializedName("cctvLongitude")
	val cctvLongitude: Double? = null,

	@field:SerializedName("cctvName")
	val cctvName: String? = null
)
