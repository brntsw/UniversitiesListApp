package com.bruno.universitieslistapp

data class University(
    val webpages: List<String>,
    val name: String,
    val countryCode: String,
    val country: String,
    val stateProvince: String,
    val domains: List<String>
)