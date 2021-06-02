package com.dicoding.picodiploma.byecorona.data

import androidx.lifecycle.LiveData
import com.dicoding.picodiploma.byecorona.data.model.Cluster

interface ByeCoronaDataSource {
    fun getListCluster(): LiveData<List<Cluster>>
}