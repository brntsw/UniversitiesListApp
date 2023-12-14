package com.bruno.universitieslistapp

import com.bruno.universitieslistapp.ui.UniversitiesListViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock

class UniversitiesListViewModelTest {

    private lateinit var viewModel: UniversitiesListViewModel
    private val repository: UniversitiesRepository = mock<UniversitiesRepository> {
        (UniversitiesRepository::class.java)
    }
    private val testScheduler = TestScheduler()

    @Before
    fun setUp() {
        // Initialize RxJavaPlugins with TestScheduler for testing
        RxJavaPlugins.setIoSchedulerHandler { testScheduler }

        viewModel = UniversitiesListViewModel(repository)
    }

    @Test
    fun `loadUniversities posts values to LiveData`() {
        // Arrange
        val universities = listOf(
            University(
                listOf("link1", "link2"),
                "Test University",
                "US",
                "US",
                "AL",
                listOf("domain1", "domain2")
            )
        )
        `when`(repository.getUniversities()).thenReturn(Single.just(universities))

        // Act
        viewModel.loadUniversities()
        testScheduler.triggerActions() // Trigger the RxJava chain

        // Assert
        viewModel.universities.observeForever {
            Assert.assertNotNull(it)
            Assert.assertEquals("Test University", it[0].name)
        }
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
    }
}