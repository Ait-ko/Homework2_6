package com.example.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel(){
    fun getAllData(): LiveData<List<Character>> {
        return repository.getCharacters()
    }
}