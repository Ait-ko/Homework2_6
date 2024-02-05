package com.example.data

import com.example.model.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CartoonApiService {
    @GET("character")
    fun getCharacters(): Call<BaseResponse<Character>>

    @GET("character/{id}")
    fun getCharacter(@Path("id") id: Int): Call<Character>
}