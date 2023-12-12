package com.bruno.universitieslistapp.mapper

import com.bruno.universitieslistapp.University
import com.bruno.universitieslistapp.remote.UniversityRemote

fun List<UniversityRemote>.mapToUniversities(): List<University> {
    val universities = ArrayList<University>()
    this.forEach { remote ->
        universities.add(
            University(
                remote.webpages,
                remote.name,
                remote.countryTwoCodes,
                remote.country,
                remote.stateProvince,
                remote.domains
            )
        )
    }
    return universities
}