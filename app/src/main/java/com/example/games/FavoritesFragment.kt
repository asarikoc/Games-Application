package com.example.games

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class FavoritesFragment : Fragment() {

    lateinit var v: View

    private lateinit var myrecyclerview: RecyclerView
    private lateinit var listGames: ArrayList<Games>
    lateinit var games : Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_favorites, container, false)

        myrecyclerview = v.findViewById(R.id.favorites_recyclerview)
        myrecyclerview.layoutManager = LinearLayoutManager(activity)
        myrecyclerview.setHasFixedSize(true)

        listGames = ArrayList()
        //3498, id
        //listGames.add(Games(R.drawable.gta5, "Grand Theft Auto V", "96", "Action, shooter"))
        //4200 ,id
        //listGames.add(Games(R.drawable.portal2, "Portal 2", "95", "Action, puzzle"))

        var adapter = RecyclerViewAdapter(listGames,activity)
        myrecyclerview.adapter = adapter

        adapter.setOnItemClickListener(object: RecyclerViewAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

                val intent = Intent(activity,GamesActivity::class.java)
                intent.putExtra("id",3498)

                startActivity(intent)
            }
        })


        val swipeToDeleteCallback = object : SwipeToDeleteCallback(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                listGames.removeAt(position)
                myrecyclerview.adapter?.notifyItemRemoved(position)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)

        itemTouchHelper.attachToRecyclerView(myrecyclerview)

        return v
    }
}