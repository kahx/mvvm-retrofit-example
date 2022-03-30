package com.kaansa.nuevo.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaansa.nuevo.models.Photo
import com.kaansa.nuevo.service.RetrofitInstance
import kotlinx.coroutines.launch
import java.lang.Exception

class PhotoViewModel : ViewModel(){
    private var _photos = MutableLiveData<Photo>()
    val photos : LiveData<Photo>
        get() = _photos

    fun getData(){
        viewModelScope.launch{
            try{
                _photos.value = RetrofitInstance.retrofitInstance.getPhotoData()
            }catch (e: Exception){
                Log.e("Log", "${e.message}")
            }
        }
    }
}