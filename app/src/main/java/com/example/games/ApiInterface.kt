package com.example.games

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("games")
    fun getData(
        @Query("key") aKey: String

    ):Call<DataModel>


}