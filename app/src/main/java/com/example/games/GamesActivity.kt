package com.example.games

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_games.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class GamesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games)

        val imageGames : ImageView = findViewById(R.id.image_description_id)
        val mainGames : TextView = findViewById(R.id.desc_id)
        val favGames : TextView = findViewById(R.id.favourite_button_id)

        val bundle : Bundle?= intent.extras

        val Id = bundle?.getInt("id")
        if (Id == 3498 || Id == 4200){
            favGames.text = "Favorited"
        }

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api:ApiInterface = retrofit.create(ApiInterface::class.java)

        val call: Call<DetailResults> = api.getDetails(Id.toString(),"3be8af6ebf124ffe81d90f514e59856c")

        call.enqueue(object: Callback<DetailResults> {
            override fun onResponse(call: Call<DetailResults>, response: Response<DetailResults>) {
                mainGames.text = response.body()!!.description
                val myUrl : String = response.body()!!.backgroundImage
                Glide.with(this@GamesActivity).load(myUrl).into(imageGames)
             }
            override fun onFailure(call: Call<DetailResults?>, t: Throwable) {
                Toast.makeText(this@GamesActivity,"Error", Toast.LENGTH_SHORT).show()
            }
        })

        games_button_id.setOnClickListener{
            finish()
        }

    }
}