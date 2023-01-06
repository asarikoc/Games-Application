package com.example.games

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("games")
    fun getData(
        @Query("key") aKey: String

    ):Call<DataModel>

    @GET("games/{id}")
    fun getDetails(
        @Path("id") idGame : String,
        @Query("key") aKey: String
    ): Call<DetailResults>


}