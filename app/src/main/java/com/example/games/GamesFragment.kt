package com.example.games

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import java.util.ArrayList

class GamesFragment : Fragment() {

    lateinit var v: View

    private lateinit var myrecyclerview: RecyclerView
    private lateinit var listGames: ArrayList<Games>
    lateinit var games : Array<String>

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
        listGames.add(Games(R.drawable.gta5, "Grand Theft Auto V", "96", "Action, shooter"))
        listGames.add(Games(R.drawable.portal2, "Portal 2", "95", "Action, puzzle"))
        listGames.add(Games(R.drawable.witcher, "The Witcher 3", "89", "Action, puzzle"))
        listGames.add(Games(R.drawable.l4d2, "Left 4 Dead 2", "89", "Action, puzzle"))

        games = arrayOf(
            "Rockstar Games went bigger, since their previous installment of the series. You get the complicated and realistic world-building from Liberty City of GTA4 in the setting of lively and diverse Los Santos, from an old fan favorite GTA San Andreas. 561 different vehicles (including every transport you can operate)",
            "b",
            "c",
            "d"
        )

        var adapter = RecyclerViewAdapter(listGames)
        myrecyclerview.adapter = adapter
        adapter.setOnItemClickListener(object: RecyclerViewAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

                //Toast.makeText(activity,"you clicked on item no. $position",Toast.LENGTH_SHORT).show()


                val intent = Intent(activity,GamesActivity::class.java)
                intent.putExtra("imageID",listGames[position].titleImage)
                intent.putExtra("heading",listGames[position].heading)
                intent.putExtra("metacritic",listGames[position].metacritic)
                intent.putExtra("genre",listGames[position].genre)
                intent.putExtra("games",games[position])
                startActivity(intent)
            }


        })
        return v
    }


}