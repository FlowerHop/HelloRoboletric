package com.flowerhop.helloroboletric

import androidx.lifecycle.MutableLiveData

class CountViewModel(private var repository: CountRepository) {
    val count: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>(repository.loadCount())
    }

    fun addCount() {
        count.value?.plus(1)?.let {
            repository.updateCount(it)
            count.postValue(it)
        }
    }
}
