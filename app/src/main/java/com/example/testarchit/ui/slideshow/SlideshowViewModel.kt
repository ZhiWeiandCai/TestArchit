package com.example.testarchit.ui.slideshow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class SlideshowViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }
    val text: LiveData<String> = _text

    private val _texts = MutableLiveData<List<TableDish>>().apply {
        value = (1..16).mapIndexed { _, i ->
            TableDish(i,"Table $i", listOf(
                DishItem("dish $i", 2),
                DishItem("dish $i", 2),
                DishItem("dish $i", 2),
                DishItem("dish $i", 2),
                DishItem("dish $i", 2),
                DishItem("dish $i", 2)
            ))
        }
    }

    val texts: LiveData<List<TableDish>> = _texts

    init {
        startTask()
    }

    private fun startTask() {
        // 创建一个协程，并使用定时器定期执行任务
        viewModelScope.launch(Dispatchers.IO) {
            // 任务的执行间隔，这里设置为16秒
            val intervalInMillis = 5000L

            // 循环执行任务，直到协程被取消（即 ViewModel 被清理或销毁）
            while (isActive) {
                // 执行你的任务
                Log.i("SlideshowViewModel", "task")

                // 等待一段时间，模拟定时器的间隔
                delay(intervalInMillis)
            }
        }
    }

    override fun onCleared() {
        Log.i("SlideshowViewModel", "onCleared")
    }
}