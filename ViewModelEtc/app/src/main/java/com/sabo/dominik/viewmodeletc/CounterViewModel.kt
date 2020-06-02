package com.sabo.dominik.viewmodeletc

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {
    val currentCount: MutableLiveData<Int> by lazy{
        MutableLiveData<Int>()
    }

    val currentColour: MutableLiveData<Int> by lazy{
        MutableLiveData<Int>()
    }

    fun increment(){
        currentCount.value = currentCount.value?.plus(1)
    }

    fun reset(){
        currentCount.value = 0
        currentColour.value = Color.CYAN
    }
}