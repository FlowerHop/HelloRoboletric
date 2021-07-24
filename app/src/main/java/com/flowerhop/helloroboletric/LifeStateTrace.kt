package com.flowerhop.helloroboletric

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class LifeStateTrace: LifecycleObserver {
    var state: LifeState = LifeState.Initialized

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun toCreatedFromInitialized() {
        state = LifeState.Created
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun toStartedFromCreated() {
        state = LifeState.Started
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun toResumed() {
        state = LifeState.Resumed
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private fun toStartedFromResumed() {
        state = LifeState.Started
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun toCreatedFromStarted() {
        state = LifeState.Created
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun toDestroyed() {
        state = LifeState.Destroyed
    }
}

enum class LifeState {
    Initialized, Destroyed, Created, Started, Resumed,
}