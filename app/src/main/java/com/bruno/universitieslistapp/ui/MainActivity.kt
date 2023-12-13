package com.bruno.universitieslistapp.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bruno.universitieslistapp.R
import com.bruno.universitieslistapp.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val universitiesListViewModel: UniversitiesListViewModel by viewModel()
    private lateinit var universitiesViewDelegate: UniversitiesViewDelegate

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        universitiesViewDelegate = UniversitiesViewDelegateImpl(binding, this, universitiesListViewModel)

        universitiesViewDelegate.showData()
    }

    override fun onDestroy() {
        super.onDestroy()
        universitiesViewDelegate.dispose()
    }
}