package com.example.data

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import mbk.io.sabrina_hm1_6m.utils.Resource
import mbk.io.sabrina_hm1_6m.utils.showToast

abstract class BaseActivity : AppCompatActivity() {

    fun <T> LiveData<Resource<T>>.stateObserver(
        success: (data: T) -> Unit,
        state: ((res: Resource<T>) -> Unit)? = null
    ) {
        observe(this@BaseActivity) {res->
            if (state != null) {
                state(res)
            }
            when (res) {
                is Resource.Error -> {
                    res.message?.let {
                        this@BaseActivity.showToast(it)
                    }
                }

                is Resource.Loading -> {}
                is Resource.Success -> {
                    if (res.data != null) {
                        success(res.data)
                    }
                }
            }
        }
    }

}