package com.example.games;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GamesFragment extends Fragment {

    View v;
    private RecyclerView myrecyclerview ;
    private List<Games> listGames;

    public GamesFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
        v = inflater.inflate(R.layout.fragment_games,container,false);
        myrecyclerview = (RecyclerView) v.findViewById(R.id.games_recyclerview);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(),listGames);
        myrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        myrecyclerview.setAdapter(recyclerAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        listGames = new ArrayList<>();
        listGames.add(new Games(R.drawable.gta5,"Grand Theft Auto V","96","Action, shooter"));
        listGames.add(new Games(R.drawable.portal2,"Portal 2","95","Action, puzzle"));
        listGames.add(new Games(R.drawable.witcher,"The Witcher 3","89","Action, puzzle"));
        listGames.add(new Games(R.drawable.l4d2,"Left 4 Dead 2","89","Action, puzzle"));

    }

}
