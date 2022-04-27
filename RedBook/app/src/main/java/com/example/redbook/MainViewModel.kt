package com.example.redbook

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.redbook.network.RedBookApi
import kotlinx.coroutines.launch
import java.lang.Exception
import java.lang.Thread.sleep

class MainViewModel : ViewModel() {
    private  val _displayData = MutableLiveData<List<DisplayData>>()
    val displayData:LiveData<List<DisplayData>>
        get() = _displayData

    init{
        getRedBookProperty()
    }

    private fun getRedBookProperty(){
        viewModelScope.launch {
            try{
                _displayData.value = RedBookApi.retrofitService.getProperty().data.getDisplayData()
            }catch (e:Exception){
                Log.d("displayDataFail", "Failure: ${e.message}")
            }
        }
    }
}