package com.flowerhop.helloroboletric

import androidx.appcompat.widget.AppCompatButton
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController

@RunWith(RobolectricTestRunner::class)
class HelloRoboletricTest {
    @Test
    fun `When clicking button, text changes`() {
        // Arrange
        val mainActivity = Robolectric.setupActivity(MainActivity::class.java)
        val btn = mainActivity.findViewById<AppCompatButton>(R.id.btnHello)
        val oriText = btn.text

        // Act
        btn.performClick()
        val newTest = btn.text

        // Assert
        Assert.assertNotEquals(oriText, newTest)
    }

    @Test
    fun `Show Click 3 when clicking 3 times`() {
        // Arrange
        val mainActivity = Robolectric.setupActivity(MainActivity::class.java)
        val btn = mainActivity.findViewById<AppCompatButton>(R.id.btnHello)
        val expected = "Click 3"

        // Act
        btn.performClick()
        btn.performClick()
        btn.performClick()

        // Assert
        Assert.assertEquals(expected, btn.text)
    }

    @Test
    fun `Test LifeStateTrace is Resumed`() {
        // Arrange
        val activityController = Robolectric.buildActivity(MainActivity::class.java)
        val expected = LifeState.Resumed

        // Act
        activityController.create().start().resume()
        val state = (activityController.get() as MainActivity).stateTrace.state

        // Assert
        Assert.assertEquals(expected, state)
    }

    @Test
    fun `Test LifeStateTrace is Destroyed`() {
        // Arrange
        val activityController = Robolectric.buildActivity(MainActivity::class.java)
        val expected = LifeState.Destroyed

        // Act
        activityController.create().start().resume().pause().stop().destroy()
        val state = (activityController.get() as MainActivity).stateTrace.state

        // Assert
        Assert.assertEquals(expected, state)
    }
}