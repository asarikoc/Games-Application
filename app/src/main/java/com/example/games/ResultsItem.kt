package com.example.games

import com.google.gson.annotations.SerializedName

data class ResultsItem(@SerializedName("rating")
                       val rating: Double = 0.0,
                       @SerializedName("metacritic")
                       val metacritic: Int = 0,
                       @SerializedName("genres")
                       val genres: List<GenresItem>?,
                       @SerializedName("id")
                       val id: Int = 0,
                       @SerializedName("background_image")
                       val backgroundImage: String = "",
                       @SerializedName("name")
                       val name: String = "")