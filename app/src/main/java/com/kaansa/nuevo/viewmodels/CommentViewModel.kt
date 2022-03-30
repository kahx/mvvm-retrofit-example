package com.kaansa.nuevo.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaansa.nuevo.models.Comment
import com.kaansa.nuevo.service.RetrofitInstance
import kotlinx.coroutines.launch
import java.lang.Exception

class CommentViewModel : ViewModel(){
    private var _comments = MutableLiveData<Comment>()
    val comments : LiveData<Comment>
        get() = _comments

    fun getData(i: Int) {
        viewModelScope.launch{
            try{
                _comments.value = RetrofitInstance.retrofitInstance.getCommentData(i)
            }catch (e: Exception){
                Log.e("Log", "${e.message}")
            }
        }
    }
}