package com.bruno.universitieslistapp.remote

import com.bruno.network_universities.UniversitiesRemoteService
import com.bruno.universitieslistapp.UniversitiesRepository
import com.bruno.universitieslistapp.University
import com.bruno.universitieslistapp.mapper.mapToUniversities
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class UniversitiesRemoteImpl(private val service: UniversitiesRemoteService) : UniversitiesRepository {
    override fun getUniversities(): Single<List<University>> {
        return service.getUniversities("United States")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { universities ->
                universities.sortedBy { it.name }.mapToUniversities()
            }
    }
}