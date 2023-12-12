package com.bruno.universitieslistapp.di

import com.bruno.universitieslistapp.UniversitiesRepository
import com.bruno.universitieslistapp.remote.UniversitiesRemoteImpl
import com.bruno.universitieslistapp.remote.UniversitiesRemoteService
import com.bruno.universitieslistapp.ui.UniversitiesListViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import timber.log.Timber
import java.util.concurrent.TimeUnit

fun getApplicationModule() = module {
    single { UniversitiesRemoteImpl(get()) }
    single<UniversitiesRepository> {
        UniversitiesRemoteImpl(get()) // or UniversitiesLocalImpl()
    }
    viewModel {
        UniversitiesListViewModel(repository = get())
    }
}

fun getNetworkModule() = module {
    factory { provideLoggingInterceptor() }
    factory { provideOkHttpClient(get()) }
    factory { provideUniversitiesRemoteService(get()) }
}

private fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    val builder = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)

    return builder.build()
}

private fun provideLoggingInterceptor() : HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor { message: String? ->
        Timber.tag("OkHttp").d(message)
    }
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

private fun provideUniversitiesRemoteService(okHttpClient: OkHttpClient): UniversitiesRemoteService {
    return UniversitiesRemoteService.Builder().makeUniversitiesService(okHttpClient)
}