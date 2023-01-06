package com.example.games

import com.google.gson.annotations.SerializedName

data class DetailDataModel(@SerializedName("detailResults")
                           val results: List<DetailResults>?)
