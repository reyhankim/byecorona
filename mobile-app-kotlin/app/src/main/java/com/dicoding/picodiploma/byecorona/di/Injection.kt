package com.dicoding.picodiploma.byecorona.di

import android.content.Context
import com.dicoding.picodiploma.byecorona.data.ByeCoronaRepository
import com.dicoding.picodiploma.byecorona.data.source.remote.RemoteDataSource

object Injection {
    fun provideRepository(context: Context): ByeCoronaRepository {

        val remoteDataSource = RemoteDataSource()

        return ByeCoronaRepository.getInstance(remoteDataSource)
    }
}