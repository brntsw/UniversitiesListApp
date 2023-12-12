package com.bruno.universitieslistapp.remote

import com.squareup.moshi.Json

data class UniversityRemote(
    val webpages: List<String>,
    val name: String,
    @field:Json(name = "alpha_two_code") val countryTwoCodes: String,
    val country: String,
    @field:Json(name = "state-province") val stateProvince: String,
    val domains: List<String>
)