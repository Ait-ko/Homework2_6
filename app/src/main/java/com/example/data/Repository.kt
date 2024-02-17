package com.example.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.model.BaseResponse
import com.example.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository(private val api: CartoonApiService) : BaseRepository() {
    fun getCharacters(): LiveData<Resource<BaseResponse>> = doRequest {
        api.getCharacters()
    }

    fun getCharacter(id: Int): LiveData<Resource<Character>> = doRequest {
        api.getCharacter(id)
    }

}

/*   fun getCharacter(id: Int): LiveData<Resource<Character>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            try {
                val response = api.getCharacter(id)
                if (response.isSuccessful && response.body() != null && response.code() in 200..300) {
                    emit(Resource.Success(response.body()!!))
                }
            } catch (io: IOException) {
                emit(Resource.Error(io.message ?: "Unknown Error!"))
            }
        }
    }*/

