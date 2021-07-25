package com.flowerhop.helloroboletric

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class CountViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `Default count is 0`() {
        // Arrange
        val repo = CountRepository()
        val viewModel = CountViewModel(repo)
        val expected = 0

        // Act
        val count = viewModel.count.value

        // Assert
        Assert.assertEquals(expected, count)
    }

    @Test
    fun `Observe count becomes 2 when addCount is invoked twice`() {
        // Arrange
        val repo = CountRepository()
        val viewModel = CountViewModel(repo)
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