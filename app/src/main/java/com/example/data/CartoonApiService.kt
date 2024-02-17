package com.example.data

import com.example.model.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CartoonApiService {
    @GET("character")
    suspend fun getCharacters(): Response<BaseResponse>

    @GET("character/{id}")
    suspend fun getCharacter(@Path("id") id: Int): Response<Character>
}