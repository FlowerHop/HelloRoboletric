package com.flowerhop.helloroboletric

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CountViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    lateinit var repo: CountRepository
    lateinit var viewModel: CountViewModel

    @Before
    fun setUp() {
        repo = CountRepository.create(ApplicationProvider.getApplicationContext())
        viewModel = CountViewModel(repo)
    }

    @Test
    fun `Default count is 0`() {
        // Arrange
        val expected = 0

        // Act
        val count = viewModel.count.value

        // Assert
        Assert.assertEquals(expected, count)
    }

    @Test
    fun `Observe count becomes 2 when addCount is invoked twice`() {
        // Arrange
        val expected = 2
        var count = viewModel.count.value
        val observer = Observer<Int> {
                t -> count = t
        }
        viewModel.count.observeForever(observer)

        // Act
        viewModel.addCount()
        viewModel.addCount()

        // Assert
        Assert.assertEquals(expected, count)
        viewModel.count.removeObserver(observer)
    }
}