package com.example.games;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext ;
    List<Games> mData;
    Dialog myDialog;

    public RecyclerViewAdapter(Context mContext, List<Games> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v ;
        v = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        myDialog = new Dialog(mContext);
        myDialog.setContentView(R.layout.activity_games);

        vHolder.list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView activity_heading = (TextView) myDialog.findViewById(R.id.heading_id);
                TextView activity_metacritic = (TextView) myDialog.findViewById(R.id.metacritic_id);
                TextView activity_genre = (TextView) myDialog.findViewById(R.id.genre_id);

                activity_heading.setText(mData.get(vHolder.getAdapterPosition()).getHeading());
                activity_metacritic.setText(mData.get(vHolder.getAdapterPosition()).getMetacritic());
                activity_genre.setText(mData.get(vHolder.getAdapterPosition()).getGenre());

                myDialog.show();
            }
        });

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.img.setImageResource(mData.get(position).getTitleImage());
        holder.tv_heading.setText(mData.get(position).getHeading());
        holder.tv_metacritic.setText(mData.get(position).getMetacritic());
        holder.tv_genre.setText(mData.get(position).getGenre());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout list_item;
        private ImageView img;
        private TextView tv_heading;
        private TextView tv_metacritic;
        private TextView tv_genre;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            list_item = (ConstraintLayout) itemView.findViewById(R.id.list_item_id);
            img = (ImageView) itemView.findViewById(R.id.title_image);
            tv_heading = (TextView) itemView.findViewById(R.id.tvheading);
            tv_metacritic = (TextView) itemView.findViewById(R.id.metacritic);
            tv_genre = itemView.findViewById(R.id.genre);
        }
    }

}
