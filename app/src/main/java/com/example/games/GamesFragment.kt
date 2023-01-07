package com.example.games

import android.accounts.AccountManager.get
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Array.get
import java.util.*
import java.util.concurrent.Executors

class GamesFragment : Fragment() {

    lateinit var v: View

    private lateinit var myrecyclerview: RecyclerView
    private lateinit var listGames: ArrayList<Games>
    lateinit var games : Array<String>
    private lateinit var searchView : SearchView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_games, container, false)
        myrecyclerview = v.findViewById(R.id.games_recyclerview)
        myrecyclerview.layoutManager = LinearLayoutManager(activity)
        myrecyclerview.setHasFixedSize(true)

        listGames = ArrayList()

        val retrofit:Retrofit = Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api:ApiInterface = retrofit.create(ApiInterface::class.java)

        val call: Call<DataModel> = api.getData("3be8af6ebf124ffe81d90f514e59856c")


        call.enqueue(object: Callback<DataModel>{
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {

                if (response.isSuccessful){
                    listGames.clear()

                    for ( myData in response.body()!!.results!!){
                        var genresGame : String = ""
                        for ( myGenres in myData.genres!!){
                            genresGame += myGenres.name +" "
                        }
                        val myUrl : String = myData.backgroundImage
                        //
                        // val imageView : ImageView = v.findViewById(R.id.imageView_id)
                        //
                        //
                        // Picasso.with(context) // Context
                        //     .load(myUrl) // URL
                        //     .into(imageView) // An ImageView object to show the loaded image

                        listGames.add(
                            Games(
                                myUrl,
                                myData.name,
                                myData.metacritic.toString(),
                                genresGame
                            ))
                    }
                    val adapter = RecyclerViewAdapter(listGames, activity!!)
                    adapter.notifyDataSetChanged()
                    myrecyclerview.adapter = adapter
                    adapter?.setOnItemClickListener(object: RecyclerViewAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(activity,GamesActivity::class.java)
                            intent.putExtra("id",response.body()!!.results!![position].id)
                            startActivity(intent)
                        }
                    })

                    // Search for games
                    searchView = v.findViewById(R.id.searchView)
                    searchView.clearFocus()
                    searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return false
                        }

                        override fun onQueryTextChange(query: String?): Boolean {
                            if(query != null){
                                val filteredList = ArrayList<Games>()
                                for (i in listGames){
                                    if(i.heading.lowercase(Locale.ROOT).contains(query)){
                                        filteredList.add(i)
                                    }
                                }
                                if(filteredList.isEmpty()){
                                    Toast.makeText(activity,"No Data found", Toast.LENGTH_SHORT).show()
                                }else{
                                    adapter?.setFilteredList(filteredList)
                                }
                            }
                            return true
                        }

                    })
                }
            }
            override fun onFailure(call: Call<DataModel?>, t: Throwable) {
                Toast.makeText(activity,"Error",Toast.LENGTH_SHORT).show()
            }
        })

        return v
    }

}