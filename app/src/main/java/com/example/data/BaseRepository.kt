package com.example.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import mbk.io.sabrina_hm1_6m.data.model.Character
import mbk.io.sabrina_hm1_6m.utils.Resource
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {

    protected fun <T> doRequest(
        request: suspend () -> Response<T>
    ): LiveData<Resource<T>> = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val response = request.invoke()
            if (response.isSuccessful && response.body() != null && response.code() in 200..300) {
               response.body()?.let {
                    emit(Resource.Success(response.body()!!))
                }
            }
        } catch (io: IOException) {
            emit(Resource.Error(io.message ?: "Unknown Error!"))
        }
    }
}