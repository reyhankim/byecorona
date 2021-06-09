package com.dicoding.picodiploma.byecorona.data

import androidx.lifecycle.LiveData
import com.dicoding.picodiploma.byecorona.data.model.CCTV
import com.dicoding.picodiploma.byecorona.data.model.Cluster

interface ByeCoronaDataSource {
    fun getListCluster(): LiveData<List<Cluster>>
    fun getListCCTV(listCCTV: List<CCTV>): LiveData<List<CCTV>>
//    fun getListViolation(idCCTV: Int): LiveData<>
}