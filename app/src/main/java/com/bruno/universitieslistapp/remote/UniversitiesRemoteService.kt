package com.bruno.universitieslistapp.remote

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface UniversitiesRemoteService {
    @GET("search")
    fun getUniversities(@Query("country") country: String): Single<List<UniversityRemote>>

    class Builder {
        fun makeUniversitiesService(okHttpClient: OkHttpClient) : UniversitiesRemoteService {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://universities.hipolabs.com/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            return retrofit.create(UniversitiesRemoteService::class.java)
        }
    }
}