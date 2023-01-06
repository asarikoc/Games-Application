package com.example.games

import com.google.gson.annotations.SerializedName

data class DataModel(@SerializedName("results")
                     val results: List<ResultsItem>?)