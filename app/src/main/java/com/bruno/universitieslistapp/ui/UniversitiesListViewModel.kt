package com.bruno.universitieslistapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bruno.universitieslistapp.UniversitiesRepository
import com.bruno.universitieslistapp.University
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber

class UniversitiesListViewModel(private val repository: UniversitiesRepository) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _universities = MutableLiveData<List<University>>()
    val universities: LiveData<List<University>> = _universities

    fun loadUniversities() {
        disposable.add(
            repository.getUniversities()
            .subscribe({ universities ->
                _universities.postValue(universities)
            }, { error ->
                Timber.e(error)
            })
        )
    }

    fun dispose() {
        disposable.dispose()
    }
}