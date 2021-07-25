package com.flowerhop.helloroboletric

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CountRepositoryTest {
    lateinit var repo: CountRepository

    @Before
    fun setUp() {
        repo = CountRepository.create(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun `Default count is zero`() {
        // Arrange
        val expected = 0

        // Act
        val count = repo.loadCount()

        // Assert
        assertEquals(expected, count)
    }

    @Test
    fun `When count is added to 2, we can get 2 from it`() {
        // Arrange
        repo.updateCount(2)
        val expected = 2

        // Act
        val count = repo.loadCount()

        // Assert
        assertEquals(expected, count)
    }

    @Test
    fun `Repo can save count`() {
        // Arrange
        repo.updateCount(2)
        val expected = 2

        // Act
        val newRepo = CountRepository.create(ApplicationProvider.getApplicationContext())
        val count = newRepo.loadCount()

        // Assert
        assertEquals(expected, count)
    }
}