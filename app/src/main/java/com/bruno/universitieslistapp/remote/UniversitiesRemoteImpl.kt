package com.bruno.universitieslistapp.remote

import com.bruno.universitieslistapp.UniversitiesRepository
import com.bruno.universitieslistapp.University
import com.bruno.universitieslistapp.mapper.mapToUniversities
import io.reactivex.rxjava3.core.Single

class UniversitiesRemoteImpl(val service: UniversitiesRemoteService) : UniversitiesRepository {
    override fun getUniversities(): Single<List<University>> {
        return service.getUniversities("United States")
            .map { it.mapToUniversities() }
    }
}