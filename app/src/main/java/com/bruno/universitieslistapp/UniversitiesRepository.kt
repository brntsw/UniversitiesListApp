package com.bruno.universitieslistapp

import io.reactivex.rxjava3.core.Single

interface UniversitiesRepository {
    fun getUniversities(): Single<List<University>>
}