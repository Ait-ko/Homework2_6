package com.example.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.Repository
import com.example.model.BaseResponse
import com.example.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class MainViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _charactersLv = MutableLiveData<Resource<BaseResponse>>()
    val charactersLv: LiveData<Resource<BaseResponse>> = _charactersLv
    fun getCharacters() {
        _charactersLv.value = Resource.Loading()
        repository.getCharacters().observeForever { resource ->
            _charactersLv.value = resource
        }
    }

}