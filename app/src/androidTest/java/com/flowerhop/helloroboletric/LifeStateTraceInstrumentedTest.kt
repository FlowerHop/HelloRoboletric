package com.flowerhop.helloroboletric

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import org.junit.Assert
import org.junit.Test

class LifeStateTraceInstrumentedTest {
    @Test
    fun isResumed() {
        // Arrange
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        val expected = LifeState.Resumed

        // Act
        scenario.onActivity {
            // Assert
            Assert.assertEquals(expected, it.stateTrace.state)
        }
    }

    @Test
    fun isStartedFromResumed() {
        // Arrange
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        val expected = LifeState.Started

        // Act
        scenario.moveToState(Lifecycle.State.STARTED)
        scenario.onActivity {
            // Assert
            Assert.assertEquals(expected, it.stateTrace.state)
        }
    }

    @Test
    fun isCreatedFromStarted() {
        // Arrange
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.STARTED)
        val expected = LifeState.Created

        // Act
        scenario.moveToState(Lifecycle.State.CREATED)
        scenario.onActivity {
            // Assert
            Assert.assertEquals(expected, it.stateTrace.state)
        }
    }

    @Test
    fun isDestroyed() {
        // Arrange
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.moveToState(Lifecycle.State.STARTED)
        scenario.moveToState(Lifecycle.State.CREATED)
        val expected = true

        // Act
        scenario.moveToState(Lifecycle.State.DESTROYED)
        var hasExpectation = false
        try {
            scenario.onActivity { it.stateTrace }
        } catch (e: Exception) {
            hasExpectation = true
        }
        // Assert
        Assert.assertEquals(expected, hasExpectation)
    }
}