package com.dicoding.picodiploma.byecorona.ui.cctv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.byecorona.data.ByeCoronaRepository
import com.dicoding.picodiploma.byecorona.data.model.CCTV

class CCTVViewModel(private val byeCoronaRepository: ByeCoronaRepository) : ViewModel() {
    fun getListCCTV(listCCTV: List<CCTV>) : LiveData<List<CCTV>> = byeCoronaRepository.getListCCTV(listCCTV)
}