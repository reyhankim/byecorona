package com.dicoding.picodiploma.byecorona.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ClusterResponse(

	@field:SerializedName("cctvList")
	val cctvList: List<CctvListItem?>? = null,

	@field:SerializedName("clusterLatitude")
	val clusterLatitude: Double? = null,

	@field:SerializedName("clusterName")
	val clusterName: String? = null,

	@field:SerializedName("clusterLongitude")
	val clusterLongitude: Double? = null,

	@field:SerializedName("clusterId")
	val clusterId: Int? = null
)

data class CctvListItem(

	@field:SerializedName("cctvId")
	val cctvId: Int? = null
)
