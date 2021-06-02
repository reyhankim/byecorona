package com.dicoding.picodiploma.byecorona.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.byecorona.data.ByeCoronaRepository
import com.dicoding.picodiploma.byecorona.data.model.Cluster

class MapViewModel(private val byeCoronaRepository: ByeCoronaRepository) : ViewModel() {
    fun getListCluster() : LiveData<List<Cluster>> = byeCoronaRepository.getListCluster()
}