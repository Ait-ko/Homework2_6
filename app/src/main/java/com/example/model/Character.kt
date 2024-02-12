package com.example.model

import java.io.Serializable

data class BaseResponse<T>(
    var results: List<Character>
) : Serializable

data class Character(
    var id: Int,
    var name: String,
    var status: String,
    var gender: String,
    var image: String,
    var species: String,
    var origin: Origin,
    var location: Location
) : Serializable

data class Origin(
    var name: String
) : Serializable

data class Location(
    var name: String
) : Serializable