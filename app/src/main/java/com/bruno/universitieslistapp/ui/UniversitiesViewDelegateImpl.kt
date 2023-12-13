package com.bruno.universitieslistapp.ui

import androidx.lifecycle.LifecycleOwner
import com.bruno.universitieslistapp.databinding.ActivityMainBinding
import timber.log.Timber

class UniversitiesViewDelegateImpl(
    private val binding: ActivityMainBinding,
    private val lifecycleOwner: LifecycleOwner,
    private val viewModel: UniversitiesListViewModel
) : UniversitiesViewDelegate {

    override fun showData() {
        viewModel.universities.observe(lifecycleOwner) { universities ->
            universities.forEach { university ->
//                binding.listUniversities.adapter = someAdapter
                Timber.d("University: ${university.name}")
            }
        }

        viewModel.loadUniversities()
    }

    override fun dispose() {
        viewModel.dispose()
    }
}