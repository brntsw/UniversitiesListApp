package com.bruno.universitieslistapp.mapper

import com.bruno.network_universities.UniversityRemote
import com.bruno.universitieslistapp.University
import com.bruno.universitieslistapp.ui.UniversityView

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

fun List<University>.mapToView(): List<UniversityView> {
    val universityViews = ArrayList<UniversityView>()
    var counter = 0
    var showDifferentBackground = false
    this.forEach { university ->
        if (counter % 2 == 1) {
            showDifferentBackground = true
        }
        counter++
        universityViews.add(
            UniversityView(
                university.name,
                university.country,
                showDifferentBackground
            )
        )
    }
    return universityViews
}