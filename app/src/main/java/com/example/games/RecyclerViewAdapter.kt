package com.example.games

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.games.RecyclerViewAdapter.MyViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.imageview.ShapeableImageView
import org.w3c.dom.Text

class RecyclerViewAdapter( var gamesList: List<Games>) :
    RecyclerView.Adapter<MyViewHolder>() {

    private lateinit var  mListener : onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position: Int)

    }

    fun setOnItemClickListener(listener : onItemClickListener){

        mListener = listener

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(v,mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = gamesList[position]
        holder.img.setImageResource(currentItem.titleImage)
        holder.tv_heading.text = currentItem.heading
        holder.tv_metacritic.text = currentItem.metacritic
        holder.tv_genre.text = currentItem.genre
    }

    override fun getItemCount(): Int {
        return gamesList.size
    }

    class MyViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val img : ShapeableImageView = itemView.findViewById(R.id.title_image)
        val tv_heading : TextView = itemView.findViewById(R.id.tvheading)
        val tv_metacritic : TextView = itemView.findViewById(R.id.metacritic)
        val tv_genre : TextView = itemView.findViewById(R.id.genre)

        init {

            itemView.setOnClickListener {

                listener.onItemClick(adapterPosition)

            }

        }
    }
}