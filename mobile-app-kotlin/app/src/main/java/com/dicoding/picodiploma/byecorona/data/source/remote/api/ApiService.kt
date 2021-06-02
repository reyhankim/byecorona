package com.dicoding.picodiploma.byecorona.data.source.remote.api

import com.dicoding.picodiploma.byecorona.data.source.remote.response.ClusterResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("clusters")
    fun getListCluster() : Call<List<ClusterResponse>>
}