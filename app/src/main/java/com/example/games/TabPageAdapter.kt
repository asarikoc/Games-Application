package com.example.games

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabPageAdapter(activity: FragmentActivity, private  val tabCount: Int) : FragmentStateAdapter(activity){
    override fun getItemCount(): Int = tabCount

    override fun createFragment(position: Int): Fragment {
        return when (position){
            0 -> GamesFragment()
            1 -> FavoritesFragment()
            else -> GamesFragment()
        }
    }

}