package com.dicoding.picodiploma.byecorona.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.byecorona.data.ByeCoronaRepository
import com.dicoding.picodiploma.byecorona.di.Injection
import com.dicoding.picodiploma.byecorona.ui.cctv.CCTVViewModel
import com.dicoding.picodiploma.byecorona.ui.home.MapViewModel

class ViewModelFactory private constructor(private val mByeCoronaRepository: ByeCoronaRepository) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MapViewModel::class.java) -> {
                MapViewModel(mByeCoronaRepository) as T
            }
            modelClass.isAssignableFrom(CCTVViewModel::class.java) -> {
                CCTVViewModel(mByeCoronaRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}