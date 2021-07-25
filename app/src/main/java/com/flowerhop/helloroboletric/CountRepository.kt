package com.flowerhop.helloroboletric

class CountRepository {
    private var sharedCount = 0

    fun loadCount(): Int {
        return sharedCount
    }

    fun updateCount(count: Int) {
        sharedCount = count
    }
}
