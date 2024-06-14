package com.example.testarchit.ui.reflow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testarchit.net.http.HttpApiService
import com.example.testarchit.net.http.NetworkCallback
import com.example.testarchit.net.http.ResultResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReflowViewModel : ViewModel() {
    companion object {
        private const val TAG = "ReflowViewModel"
    }
    private val _text = MutableLiveData<String>().apply {
        value = "This is reflow Fragment"
    }
    val text: LiveData<String> = _text

    private val _resultResponse = MutableLiveData<ResultResponse>()
    val resultResponse = _resultResponse as LiveData<ResultResponse>

    fun getConnectResponse() {
        viewModelScope.launch(Dispatchers.IO) {
            HttpApiService.sendRequest(1, object : NetworkCallback<ResultResponse>() {
                override fun onSuccessResponse(t: ResultResponse) {
                    Log.w(TAG, "onSuccessResponse-code=" + t.resultCode + " msg=" + t.message)
                    _resultResponse.postValue(t)
                }

                override fun onFailureResponse(t: ResultResponse) {

                }
            })
        }
    }
}