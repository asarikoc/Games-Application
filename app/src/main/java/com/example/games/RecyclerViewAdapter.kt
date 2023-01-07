package com.example.games

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.games.RecyclerViewAdapter.MyViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide

class RecyclerViewAdapter( var gamesList: List<Games>, private val adapterContext: Context?) :
    RecyclerView.Adapter<MyViewHolder>() {

    private lateinit var  mListener : onItemClickListener

    interface onItemClickListener{

        fun onItemClick(position: Int)

    }

    fun setOnItemClickListener(listener : onItemClickListener){

        mListener = listener

    }

    fun setFilteredList(gamesList: List<Games>){
        this.gamesList = gamesList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(v,mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = gamesList[position]
        val circularProgressDrawable = CircularProgressDrawable(adapterContext!!)

        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(holder.img.context)
            .load(currentItem.titleImage)
            .placeholder(circularProgressDrawable)
            .into(holder.img)

        holder.tv_heading.text = currentItem.heading
        holder.tv_metacritic.text = currentItem.metacritic
        holder.tv_genre.text = currentItem.genre
    }

    override fun getItemCount(): Int {
        return gamesList.size
    }

    class MyViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        var img : ImageView = itemView.findViewById(R.id.title_image)
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