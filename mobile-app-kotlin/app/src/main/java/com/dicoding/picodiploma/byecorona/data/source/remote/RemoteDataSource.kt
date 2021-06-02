package com.dicoding.picodiploma.byecorona.data.source.remote

import android.util.Log
import com.dicoding.picodiploma.byecorona.data.source.remote.api.ApiConfig
import com.dicoding.picodiploma.byecorona.data.source.remote.response.ClusterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    fun getListCluster(callback: LoadClusterResponse) {
        val client = ApiConfig.getApiService().getListCluster()
        client.enqueue(object : Callback<List<ClusterResponse>> {
            override fun onResponse(
                call: Call<List<ClusterResponse>>,
                response: Response<List<ClusterResponse>>
            ) {
                if (response.isSuccessful) {
                    val clusterResponse = response.body() as List<ClusterResponse>
                    Log.d("haha", clusterResponse.toString())
                    callback.onAllClusterReceived(clusterResponse)
                } else {
                    Log.d("haha", response.body()?.toString().toString())
                }
            }

            override fun onFailure(call: Call<List<ClusterResponse>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    interface LoadClusterResponse {
        fun onAllClusterReceived(clusterResponse: List<ClusterResponse>)
    }
}