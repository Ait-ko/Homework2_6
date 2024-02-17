package com.example.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.Repository
import com.example.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class SecondViewModel(private val repository: Repository) : ViewModel() {

    private val _charactersLv = MutableLiveData<Resource<Character>>()
    val charactersLv: LiveData<Resource<Character>> = _charactersLv
    fun getCharacter(id: Int) {
        _charactersLv.value = Resource.Loading()
        repository.getCharacter(id).observeForever { resource ->
            _charactersLv.value = resource
        }
    }
}