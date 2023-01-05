package com.example.games

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_games.*

class GamesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games)

        val imageGames : ImageView = findViewById(R.id.image_description_id)
        val mainGames : TextView = findViewById(R.id.desc_id)


        val bundle : Bundle?= intent.extras

        //TODO: id alındı. Id ile yeni api servisi oluştur desc sayfasını doldur.
        val Id = bundle?.getInt("id")
        val imageId = bundle?.getInt("imageID")
        val games = bundle?.getString("games")

        if (imageId != null) {
            imageGames.setImageResource(imageId)
        }
        mainGames.text = games


        games_button_id.setOnClickListener{
            finish()
        }

    }
}