package com.example.games

import com.google.gson.annotations.SerializedName

data class GenresItem(@SerializedName("name")
                      val name: String = "")