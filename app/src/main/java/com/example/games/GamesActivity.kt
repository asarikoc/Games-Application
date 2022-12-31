package com.example.games

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class GamesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games)

        val imageGames : ImageView = findViewById(R.id.image_description_id)
        val headingGames : TextView = findViewById(R.id.heading_id)
        val metacriticGames : TextView = findViewById(R.id.metacritic_id)
        val genreGames : TextView = findViewById(R.id.genre_id)
        val mainGames : TextView = findViewById(R.id.desc_id)

        val bundle : Bundle?= intent.extras

        val heading = bundle!!.getString("heading")
        val imageId = bundle.getInt("imageID")
        val metacritic = bundle.getString("metacritic")
        val genre = bundle.getString("genre")
        val games = bundle.getString("games")

        imageGames.setImageResource(imageId)
        headingGames.text = heading
        metacriticGames.text = metacritic
        genreGames.text = genre
        mainGames.text = games

    }
}