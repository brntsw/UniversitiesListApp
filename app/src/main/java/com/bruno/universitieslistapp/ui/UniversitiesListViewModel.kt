package com.bruno.universitieslistapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bruno.universitieslistapp.UniversitiesRepository
import com.bruno.universitieslistapp.mapper.mapToView
import io.reactivex.rxjava3.disposables.CompositeDisposable
import timber.log.Timber

class UniversitiesListViewModel(private val repository: UniversitiesRepository) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _universities = MutableLiveData<List<UniversityView>>()
    val universities: LiveData<List<UniversityView>> = _universities

    fun loadUniversities() {
        disposable.add(
            repository.getUniversities()
                .map { universities ->
                    universities.mapToView()
                }
                .subscribe({ universityViews ->
                    _universities.postValue(universityViews)
                           }, { error ->
                               Timber.e(error)
                           })
        )
    }

    fun dispose() {
        disposable.dispose()
    }
}