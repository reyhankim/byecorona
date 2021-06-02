package com.dicoding.picodiploma.byecorona.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.picodiploma.byecorona.data.model.CCTV
import com.dicoding.picodiploma.byecorona.data.model.Cluster
import com.dicoding.picodiploma.byecorona.data.source.remote.RemoteDataSource
import com.dicoding.picodiploma.byecorona.data.source.remote.response.CctvListItem
import com.dicoding.picodiploma.byecorona.data.source.remote.response.ClusterResponse

class ByeCoronaRepository private constructor(private val remoteDataSource: RemoteDataSource) : ByeCoronaDataSource {
    companion object {
        @Volatile
        private var instance: ByeCoronaRepository? = null
        fun getInstance(
            remoteData: RemoteDataSource,
        ): ByeCoronaRepository =
            instance ?: synchronized(this) {
                instance ?: ByeCoronaRepository(
                    remoteData,
                ).apply { instance = this }
            }
    }

    override fun getListCluster(): LiveData<List<Cluster>> {
        val clusterResult = MutableLiveData<List<Cluster>>()

        remoteDataSource.getListCluster(object : RemoteDataSource.LoadClusterResponse {
            override fun onAllClusterReceived(clusterResponse: List<ClusterResponse>) {
                val clusterList = ArrayList<Cluster>()
                for (response in clusterResponse) {
                    val cluster = Cluster(
                        response.clusterId,
                        response.clusterName,
                        mapResponsesCCTV(response.cctvList),
                        response.clusterLongitude,
                        response.clusterLatitude
                    )
                    clusterList.add(cluster)
                }
                clusterResult.postValue(clusterList)
            }

        })

        return clusterResult
    }

    fun mapResponsesCCTV(input: List<CctvListItem?>?): List<CCTV> {
        val cctvList = ArrayList<CCTV>()
        input?.map {
            val cctv = CCTV(
                it?.cctvId,
                null,
                null
            )
            cctvList.add(cctv)
        }
        return cctvList
    }
}